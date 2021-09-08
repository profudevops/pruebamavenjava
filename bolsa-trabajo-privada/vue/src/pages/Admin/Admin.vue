<template>
  <div class="admin">
    <b-container class="layout" fluid>
      <b-row>
        <b-col class="pr-0 sidebar-wrapper" :class="showMobileMenu ? 'open' : ''">
          <Sidebar @closeSession="closeSession"/>
        </b-col>
        <b-col class="px-0">
          <Header />
          <div class="content-view">
            <router-view></router-view>
          </div>
        </b-col>
      </b-row>
    </b-container>
    <UpdateToken :remainingTime="remainingTime" @closeSession="closeSession" @updateTime="updateTime" @logout="logout" />
  </div>
</template>

<style lang="scss" scoped>
  @import "./Admin.scss";
</style>

<script>
  import authSrv from "@/services/auth";

  // C O M P O N E N T S
  import Sidebar from "@/components/Sidebar/Sidebar.vue";
  import Header from "@/components/Header/Header.vue";
  import UpdateToken from "@/components/Modal/UpdateToken.vue";

  import { mapState } from "vuex";

  import moment from "moment";

  let userTimeSession = 0;
  let modalTimeout = null;
  let sessionTimeout = null;
  let timeoutMouse = null;
  let sessionTimeoutTimestampt = 0;
  let mouseActivityTimer = 20000;
  export default {
    components: {
      Sidebar,
      Header,
      UpdateToken
    },

    data() {
      return {
        timeSession: 0,
        remainingTime: moment.utc(59000).format("ss")
      };
    },

    computed: {
      ...mapState("general", ["showMobileMenu"])
    },

    created() {
      this.getUserTimeSession();
    },

    watch: {
      $route() {
        this.$store.commit("general/TWICE_MOBILE_MENU", false);
      }
    },

    methods: {
      getUserTimeSession() {
        authSrv.getUserTimeSession().then(time => {
          console.log("Token actual: " + sessionStorage.getItem('token'));
          console.log("Tiempo restante: " + time + " --> " + (time/60000) + " minutos");
          console.log("sesión inicia a: " + this.getActualTime())
          userTimeSession = this.timeValidation(time);
          if(userTimeSession !== 0){
            this.handleSession(userTimeSession);
          }else{
            this.$notify({
              type: 'error',
              title: 'Error interno',
              text: 'Por el momento no es posible mantener la sesión.',
            });
            this.closeSession();
          }
        });
      },

      updateSession() {
        console.log("updateSession " + this.getActualTime());
        authSrv.updateToken().then(({ headers }) => {
          const token = headers.token;
          sessionStorage.setItem('token', token);
          this.getUserTimeSession();
        });
      },

      logout() {
        var actualTime = (new Date()).getTime();
        var remainingTime = userTimeSession  - (actualTime - sessionTimeoutTimestampt);
        console.log("Se cierra sesión con " + (remainingTime/60000) + " minutos restantes");
        console.log("sesión termina a: " + this.getActualTime())
        this.clearTimeouts();
        this.$store.dispatch("auth/logout");
      },

      handleSession(time) {
        sessionTimeoutTimestampt = (new Date()).getTime();
        remainingTime: moment.utc(59000).format("ss")
        sessionTimeout = setTimeout(() => {
          this.activeTimeoutMouse();
          this.handleMouseEvnt();
        }, time);
      },

      activeTimeoutMouse() {
        console.log("activeTimeoutMouse " + this.getActualTime());
        timeoutMouse = setTimeout(() => {
          this.mouseEvnt(false);
          this.showCloseSession();
        }, mouseActivityTimer);
      },

      handleMouseEvnt() {
        console.log("setting mousemove event");
        document.body.addEventListener("mousemove", this.mouseEvnt);
      },

      mouseEvnt(removeTimeout = true) {
        if (removeTimeout) {
          console.log("se detecta movimiento de mouse " + this.getActualTime());
          clearTimeout(timeoutMouse);
          this.updateSession();
        }

        document.body.removeEventListener("mousemove", this.mouseEvnt);
      },

      showCloseSession() {
        this.$bvModal.show("update-token");
        var actualTime = (new Date()).getTime();
        var remainingTime = userTimeSession  - (actualTime - sessionTimeoutTimestampt);
        console.log("Se muestra modal a los " + (remainingTime/60000) + " minutos restantes");
        console.log("modal aparece a: " + this.getActualTime());
        this.closeSessionTimeout();
      },

      closeSessionTimeout(timer = 60000) {
        modalTimeout = setTimeout(() => {
          this.remainingTime = moment.utc(timer - 1000).format("ss");
          if (timer > 0) {
            this.closeSessionTimeout(timer - 1000);
          } else {
            this.logout();
          }
        }, 1000);
      },

      updateTime(value) {
        if (value) {
          console.log("clearing timers");
          this.clearTimeouts();
          this.getUserTimeSession();
        }
      },

      closeSession() {
        this.logout();
      },

      timeValidation(time){
        if(isNaN(time)){
          return 0;
        }
        if(time > (59000 + mouseActivityTimer)){
          return time - (59000 + mouseActivityTimer);
        }else{
          return 0;
        }
      },

      clearTimeouts(){
        if(modalTimeout !== null){
          clearTimeout(modalTimeout);
          modalTimeout = null;
        }
        if(sessionTimeout !== null){
          clearTimeout(sessionTimeout);
          sessionTimeout = null;
        }
        if(timeoutMouse !== null){
          clearTimeout(timeoutMouse);
          timeoutMouse = null;
        }
      },

      getActualTime(){
        var date = new Date();
        var seconds = date.getSeconds();
        var minutes = date.getMinutes();
        var hour = date.getHours();
        return hour + ":" + minutes + ":" + seconds
      },
    }
  };
</script>

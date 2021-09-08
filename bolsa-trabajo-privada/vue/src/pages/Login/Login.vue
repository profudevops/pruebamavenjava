<template>
  <div class="d-flex login">
    <div class="d-flex form-wrapper">
      <div class="d-flex logo">
        <img src="@/assets/img/logos/profuturo-white.png" alt="Profuturo" />
      </div>
      <div class="form">
        <b-form
          novalidate
          class="form-signin"
          autocomplete="off"
          @submit.prevent="validateBeforeSubmit"
        >
          <h2>Ingresar</h2>

          <!-- Name -->
          <b-form-group :invalid-feedback="errors.first('usuario')" :state="!errors.has('usuario')">
            <b-form-input
              required
              name="usuario"
              placeholder="Nombre"
              v-model="form.usuario"
              v-validate="'required'"
              :state="errors.has('usuario') ? false : null"
            ></b-form-input>
          </b-form-group>

          <b-form-group
            :invalid-feedback="errors.first('password')"
            :state="!errors.has('password')"
          >
            <b-form-input
              required
              type="password"
              name="password"
              placeholder="Contraseña"
              v-model="form.password"
              v-validate="'required'"
              :state="errors.has('password') ? false : null"
            ></b-form-input>
          </b-form-group>

          <b-button block type="submit" variant="primary" :disabled="form.sendingForm">
            {{
            form.sendButtonText
            }}
          </b-button>
        </b-form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Login";
</style>

<script>
import { mapGetters } from 'vuex';
import CryptoJS from 'crypto-js';

const dict = {
  custom: {
    usuario: {
      required: 'El nombre es requerido',
    },
    password: {
      required: 'La contraseña es requerida',
    },
  },
};

export default {
  data() {
    return {
      form: {
        usuario: '',
        password: '',
        sendingForm: false,
        sendButtonText: 'Ingresar a mi cuenta',
      },
    };
  },

  beforeCreate: () => {
    document.body.className = 'full-page';
  },

  created() {
    this.$store.dispatch('auth/initAuth');
    this.$validator.localize('es', dict);
  },

  computed: {
    ...mapGetters({
      loginStatus: 'auth/status',
    }),
  },

  methods: {
    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) this.handleSubmit();
      });
    },

    getMsgError(status) {
      if (status === 401) {
        return 'El usuario o la contraseña son incorrectos';
      }

      return 'Ha ocurrido un error, vuelve intentarlo más tarde';
    },

    handleSubmit() {
      const { usuario, password } = this.form;
      this.form.sendButtonText = 'Enviando...';
      this.form.sendingForm = true;
      this.$notify({
        clean: true,
      });

      CryptoJS.pad.NoPadding = {pad: function(){}, unpad: function(){}};
      var leftCharacters = password.length % 16;
      var filledPwd = password;
      console.log("filled pwd: " + filledPwd);
      for(var i = 0; i < (16 - leftCharacters); i++){
        filledPwd = filledPwd + "\0";
      }
      var key  = CryptoJS.enc.Hex.parse("364D2FB569A0E24913C28A624CE1A271");
      var iv   = CryptoJS.enc.Hex.parse("00000000000000000000000000000000");
      var encrypted = CryptoJS.AES.encrypt(filledPwd, key, {iv: iv, padding: CryptoJS.pad.NoPadding});

      this.$store
        //.dispatch('auth/login', { usuario, password })
        .dispatch('auth/login', {
          usuario,
          password: encrypted.toString()
        })
        .catch(({ status }) => {
          this.$notify({
            type: 'error',
            title: 'Error',
            text: this.getMsgError(status)
          });
        })
        .finally(() => {
          this.form.sendButtonText = 'Ingresar a mi cuenta';
          this.form.sendingForm = false;
        });
    },
  },
};
</script>

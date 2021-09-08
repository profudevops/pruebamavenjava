<template>
  <header class="header">
    <b-button variant="light" class="menu-mobile" @click="togglMenuMobile">
      <i class="fas fa-bars"></i>
    </b-button>
    <div class="breadcrumb-list">
      <div v-for="(element, key) in breadcrumbs" :key="key">
        <router-link
          v-if="key + 1 < breadcrumbs.length"
          :to="{ path: element.path }"
        >{{ element.description }} {{ element.to }}</router-link>
        <span v-if="key + 1 < breadcrumbs.length">·</span>
        <strong v-if="key + 1 === breadcrumbs.length">{{ element.description }} {{ element.to }}</strong>
      </div>
    </div>
    <div v-if="userInfo" class="profile">{{ userInfo.firstName }} {{ userInfo.lastName }}</div>
  </header>
</template>

<style lang="scss" scoped>
@import "./Header.scss";
</style>

<script>
export default {
  data() {
    return {
      breadcrumbList: [],
      userInfo: null,
      startedTimer: false,
      updateToken: false,
    };
  },

  computed: {
    breadcrumbs: function breadcrumbs() {
      const pathArray = this.$route.path.split('/');
      pathArray.shift();

      const breadcrumbsList = pathArray.reduce((breadcrumbArray, path, idx) => {
        if (
          this.$route.matched[idx]
          && this.$route.matched[idx].meta.breadcrumb
        ) {
          breadcrumbArray.push({
            path: pathArray.reduce(
              (old, newValue, key) => (key <= idx ? `${old}/${newValue}` : old),
              '',
            ),
            description: this.$route.matched[idx].meta.breadcrumb,
          });
        }

        return breadcrumbArray;
      }, []);

      return breadcrumbsList;
    },
  },

  mounted() {
    this.userInfo = JSON.parse(sessionStorage.getItem('user'));
  },

  methods: {
    togglMenuMobile() {
      this.$store.commit('general/TWICE_MOBILE_MENU');
    },
  },
};
</script>

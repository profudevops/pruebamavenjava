<template>
  <div id="app">
    <div id="content">
        <router-view />
        <!-- Notification -->
        <notifications
          :duration="5000"
          :closeOnClick="true"
        />
    </div>
  </div>
</template>

<style lang="scss">
@import './styles/main.scss';
</style>

<script>
export default {

};
</script>

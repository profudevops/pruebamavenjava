<template>
  <b-form-group class="search-content">
    <b-form-input v-model="text" :placeholder="placeholder" @keyup="search"></b-form-input>
  </b-form-group>
</template>

<style lang="scss" scoped>
@import "./Search.scss";
</style>

<script>
export default {
  data() {
    return {
      text: '',
    };
  },

  props: {
    placeholder: {
      type: String,
      default: '',
    },
  },

  methods: {
    // Add delay in search event
    search: function search() {
      this.$emit('search', this.text);
    },
  },
};
</script>

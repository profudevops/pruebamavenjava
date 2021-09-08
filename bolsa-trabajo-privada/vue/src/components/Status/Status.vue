<template>
    <span>
        <i class="fas fa-circle status" :class="`status-${color}`"></i> {{ description }}
    </span>
</template>

<style lang="scss" scoped>
@import "./Status.scss";
</style>

<script>
export default {
  props: {
    color: {
      type: String,
      default: '',
    },

    description: {
      type: String,
      default: '',
    },
  },
};
</script>

<template>
  <div class="widget">
    <div class="wrap">
      <div>
        <strong class="title">{{ title }}</strong>
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Widget.scss";
</style>

<script>
export default {
  props: {
    title: String,
  },
};
</script>

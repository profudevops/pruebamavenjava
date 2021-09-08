<template>
  <div class="section-title" :class="(subtitleBlack || withSmallMargin) ? 'small': ''">
    <div class="d-flex">
      <h1 class="title">{{ title }}</h1>
      <slot></slot>
    </div>
    <h2 class="subtitle" :class="(subtitleWithoutMargin) ? 'm0': ''" v-if="subtitle">{{ subtitle }}</h2>
    <h2 class="subtitle-black" v-if="subtitleBlack">{{ subtitleBlack }}</h2>
  </div>
</template>

<style lang="scss" scoped>
@import "./SectionTitle";
</style>


<script>
export default {
  props: {
    title: {
      type: String,
      default: '',
    },

    subtitle: {
      type: String,
      default: '',
    },

    subtitleWithoutMargin: {
      type: Boolean,
      default: false,
    },

    subtitleBlack: {
      type: String,
      default: '',
    },

    withSmallMargin: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

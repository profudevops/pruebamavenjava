<template>
    <div class="success-card">
        <i class="fas fa-check icon"></i>
        <div>
            <h4>{{ title }}</h4>
            <p>{{ description }}</p>
        </div>
    </div>
</template>

<style lang="scss" scoped>
@import "./SuccessCard.scss";
</style>

<script>
export default {
  props: {
    title: {
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

<template>
  <div class="d-flex pagination">
    <b-input type="tel" v-model="current" v-mask="'###'" @keyup="setCurrentPage()"></b-input>
    <div>/{{ totalPages }}</div>
    <b-button variant="common" @click="prevPage()" :disabled="checkPrevPage()">
      <i class="fas fa-chevron-left"></i>
    </b-button>
    <b-button variant="common" @click="nextPage()" :disabled="checkNextPage()">
      <i class="fas fa-chevron-right"></i>
    </b-button>
  </div>
</template>

<style lang="scss" scoped>
@import './PaginationNav';
</style>


<script>
import { mask } from 'vue-the-mask';
import _ from 'lodash';

export default {
  directives: {
    mask,
  },

  props: {
    currentPage: Number,
    totalPages: Number,
  },

  data() {
    return {
      current: this.currentPage || 1,
    };
  },

  methods: {
    prevPage() {
      this.current = Number(this.current) - 1;
    },

    nextPage() {
      this.current = Number(this.current) + 1;
    },

    setCurrentPage: _.debounce(function debounce(text) {
      if (this.current >= this.totalPages) {
        this.current = this.totalPages;
      }

      if (!Number(this.current)) {
        this.current = 1;
      }
    }, 450),

    checkPrevPage() {
      return this.current === 1 || false;
    },

    checkNextPage() {
      return this.current === this.totalPages || false;
    },
  },

  watch: {
    current(newVal, oldVal) {
      const newv = Number(newVal);
      const oldv = Number(oldVal);

      if (newv !== oldv) {
        if (newv > 0 && newv <= this.totalPages) {
          this.$emit('updateCurrentPage', Number(this.current));
        }
      }
    },

    currentPage() {
      this.current = this.currentPage;
    },
  },
};
</script>

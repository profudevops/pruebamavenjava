<template>
  <b-modal id="analysts-list" modal-class="common-modal confirm-dialog" :hide-footer="true">
    <template slot="modal-header">
      <h5 class="modal-title">Lista de Analistas</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <ul>
        <li v-for="(analyst, index) in list" :key="index">{{analyst}}</li>
      </ul>
    </template>
  </b-modal>
</template>

<script>
export default {
  props: {
    list: {
      type: Array,
      default() {
        return [];
      },
    },
  },

  methods: {
    closeModal() {
      this.$bvModal.hide('analysts-list');
    },
  },
};
</script>

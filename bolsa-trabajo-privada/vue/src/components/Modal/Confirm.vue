<template>
  <b-modal id="confirm-dialog" modal-class="common-modal confirm-dialog" :hide-footer="hideFooter">
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close"><i class="fas fa-times"></i></button>
    </template>

    <template slot="default">
      <div v-html="content"></div>
      <div v-if="customContent">
        <slot></slot>
      </div>
    </template>

    <template slot="modal-footer">
      <b-button v-if="cancelTitle" variant="secondary" @click="closeModal(undefined, false)">{{ cancelTitle }}</b-button>
      <b-button variant="primary" @click="closeModal(undefined, true)">{{ okTitle }}</b-button>
    </template>
  </b-modal>

</template>

<script>
export default {
  props: {
    title: String,
    content: String,
    okTitle: {
      type: String,
      default: 'Aceptar',
    },
    cancelTitle: String,
    close: Function,
    customContent: Boolean,
    hideFooter: Boolean,
  },

  methods: {
    closeModal(modal = 'confirm-dialog', response = false) {
      this.close(modal, response);
    },
  },
};
</script>

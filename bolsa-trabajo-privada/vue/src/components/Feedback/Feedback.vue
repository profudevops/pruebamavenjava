<template>
  <div class="feedback">
    <div class="title">
      <i v-if="item.icono" :class="`fas fa-${ item.icono }`"></i>
      {{ item.titulo }}
      <b-link v-if="item.editable" class="edit" @click="editStep()">editar</b-link>
    </div>
    <div class="date">
      <em>Fecha:</em>
      {{ item.fecha }} -
      <span v-if="item.autor">Evento realizado por: {{ item.autor }}</span>
    </div>
    <div class="details" v-html="item.contenido"></div>
    <div class="note" v-if="item.note" v-html="item.note"></div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Feedback";
</style>

<script>
export default {
  props: {
    item: Object,
    canEdit: {
      required: false,
      type: Function,
    },
  },

  methods: {
    showModal(modal) {
      this.$bvModal.show(modal);
    },

    closeModal(modal) {
      this.$bvModal.hide(modal);
    },

    editStep() {
      this.$emit('canEdit', this.item);
    },
  },
};
</script>

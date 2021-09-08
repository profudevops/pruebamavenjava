<template>
  <b-modal id="delete-subarea" modal-class="common-modal delete-subarea">
    <template slot="modal-header">
      <h5 class="modal-title">Eliminar Subárea</h5>
      <button @click="closeModal()" class="close"><i class="fas fa-times"></i></button>
    </template>

    <template slot="default">
      <div>Si eliminas esta subárea, cualquier candidato de la cartera que tenga esta etiqueta, pasará a tener la del área correspondiente</div>
    </template>

    <template slot="modal-footer">
      <b-button variant="primary" @click="deleteSubArea()">Eliminar</b-button>
    </template>
  </b-modal>

</template>

<script>
export default {
  methods: {
    closeModal(response = false) {
      this.$emit('resetKey');
      this.$bvModal.hide('delete-subarea');
    },

    deleteSubArea() {
      this.$emit('deleteSubAreaModal', this.key);
      this.$bvModal.hide('delete-subarea');
    },
  },
};
</script>

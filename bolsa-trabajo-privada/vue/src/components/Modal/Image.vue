<template>
  <b-modal id="image-modal" modal-class="common-modal image-modal" :hide-footer="true">
    <template slot="modal-header">
      <h5 class="modal-title">Imágen de la galería</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <div class="text-center">
        <b-img :src="`/${image}`" fluid alt="Imagen de la galería"></b-img>
      </div>
    </template>
  </b-modal>
</template>

<script>
export default {
  props: {
    image: String,
  },

  methods: {
    closeModal() {
      this.$bvModal.hide('image-modal');
    },
  },
};
</script>

<template>
  <b-modal id="hiring-form-modal" modal-class="common-modal" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="close('hiring-form-modal')" class="close"><i class="fas fa-times"></i></button>
    </template>

    <template slot="default">
      <hiring-form></hiring-form>
    </template>

  </b-modal>

</template>

<script>
import HiringForm from '@/pages/Candidates/HiringForm/HiringForm.vue';

export default {
  components: {
    HiringForm,
  },

  props: {
    title: String,
    close: Function,
    prospectId: Number,
  },
};
</script>

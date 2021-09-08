<template>
  <div class="areas-list">
    <section-title title="Áreas">
      <b-button variant="primary" @click="sendToCreateAreas">Agregar Áreas</b-button>
    </section-title>

    <LoadingMsg v-if="loadingInfo" />

    <slot v-if="!loadingInfo">
      <!-- No data -->
      <div v-if="areaList.length === 0 && !isError">
        <p class="error-msg">No hay información disponible</p>
      </div>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron Áreas!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>

      <div v-if="areaList.length > 0">
        <div class="content" v-for="(area, key) in areaList" :key="key">
          <div class="header">
            <h3>{{ area.nombre }}</h3>
            <div>
              <router-link
                :to="{ name: 'edit-area', params: { id: area.idArea } }"
                class="mr-3"
              >Editar</router-link>
              <b-link class="mr-3" @click="showConfirm(area)">Eliminar</b-link>
              <b-link role="button" @click="collapseArea(key)">
                <i class="fas fa-chevron-down" :data-fa="key"></i>
              </b-link>
            </div>
          </div>
          <p class="description">{{ area.descripcion }}</p>
          <div class="subarea-container d-none" :data-child="key">
            <strong>Subáreas</strong>
            <ul>
              <li v-for="(subarea, key) in area.subareas" :key="key">{{subarea.nombre}}</li>
            </ul>
          </div>
        </div>
      </div>
    </slot>

    <!-- Confirm modal -->
    <confirm
      v-if="selectedArea"
      title="Eliminar área"
      :content="`¿Estas seguro que quieres eliminar el área: <b>${ selectedArea.nombre }</b>?`"
      okTitle="Eliminar área"
      :close="hideConfirm"
    ></confirm>
  </div>
</template>

<style lang="scss" scoped>
@import "./List.scss";
</style>

<script>
import areaReq from '@/services/areas';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Confirm from '@/components/Modal/Confirm.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    SectionTitle,
    Confirm,
    LoadingMsg,
  },

  data() {
    return {
      loadingInfo: false,
      selectedArea: null,
      areaList: [],
      isError: false,
    };
  },

  created() {
    this.getAreas();
  },

  methods: {
    showConfirm(area) {
      this.selectedArea = area;
      this.$notify({ clean: true });

      setTimeout(() => {
        this.$bvModal.show('confirm-dialog');
      }, 100);
    },

    hideConfirm(modal, response) {
      this.$bvModal.hide(modal);
      if (response) {
        this.deleteArea();
      }
    },

    deleteArea() {
      areaReq.deleteArea(this.selectedArea.idArea).then(() => {
        this.$notify({
          type: 'info',
          title: 'Información actualizada',
          text: `El área: ${this.selectedArea.nombre}, fue eliminada exitosamente`,
        });
        this.getAreas();
      });
    },

    sendToCreateAreas() {
      this.$router.push({ name: 'create-area' });
    },

    getAreas() {
      this.loadingInfo = true;
      this.areaList = [];

      areaReq
        .getAreaList()
        .then(({ data: { entity } }) => {
          this.areaList = entity;
          if (this.selectedArea && this.selectedArea.idArea) {
            this.areaList = this.areaList.filter(
              e => e.nombre !== this.selectedArea.nombre,
            );
            this.selectedArea = null;
          }
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    collapseArea(key) {
      const $element = document.querySelector(`[data-child="${key}"]`);
      const $fa = document.querySelector(`[data-fa="${key}"]`);

      if ($element.classList.contains('d-none')) {
        $element.classList.remove('d-none');

        $fa.classList.remove('fa-chevron-down');
        $fa.classList.add('fa-chevron-up');
      } else {
        $element.classList.add('d-none');

        $fa.classList.remove('fa-chevron-up');
        $fa.classList.add('fa-chevron-down');
      }
    },
  },
};
</script>

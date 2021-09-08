<template>
  <div>
    <slot v-if="loadingInfo">
      <div class="loading">Obteniendo información...</div>
    </slot>

    <slot v-if="!loadingInfo">
      <div v-if="!entity" class="loading">No se encontró información disponible.</div>
      <div v-if="entity">
        <!-- Title -->
        <section-title
          title="Nombre Candidato"
          subtitle="Solicitud de empleo"
          :subtitle-without-margin="true"
        />

        <!-- Tabs -->
        <b-tabs class="special-nav" fill>
          <b-tab title="Datos Personales" active>
            <PersonalInfo v-if="entity.etapa1" :info="entity.etapa1" />
          </b-tab>
          <b-tab title="Historial Laboral">
            <WorkHistory v-if="entity.etapa2" :info="entity.etapa2" />
          </b-tab>
          <b-tab title="Datos Familiares">
            <FamilyInfo v-if="entity.etapa3" :info="entity.etapa3" />
          </b-tab>
          <b-tab title="Otros Datos">
            <OtherInfo v-if="entity.etapa4" :info="entity.etapa4" />
          </b-tab>
        </b-tabs>
      </div>
    </slot>
  </div>
</template>

<script>
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';

// Forms
import PersonalInfo from './PersonalInfo/PersonalInfo.vue';
import WorkHistory from './WorkHistory/WorkHistory.vue';
import FamilyInfo from './FamilyInfo/FamilyInfo.vue';
import OtherInfo from './OtherInfo/OtherInfo.vue';

import { getAplication } from '@/services/hiring';

export default {
  data() {
    return {
      loadingInfo: false,
      entity: null,
    };
  },

  components: {
    SectionTitle,
    PersonalInfo,
    WorkHistory,
    FamilyInfo,
    OtherInfo,
  },

  created() {
    const { vacancyId } = this.$route.params;
    this.loadingInfo = true;

    getAplication(Number(vacancyId))
      .then(({ entity }) => {
        this.entity = entity;
      })
      .finally(() => {
        this.loadingInfo = false;
      });
  },
};
</script>

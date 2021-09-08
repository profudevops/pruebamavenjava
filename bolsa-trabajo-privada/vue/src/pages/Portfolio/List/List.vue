<template>
  <div>
    <section-title title="Cartera de Candidatos"></section-title>

    <!-- Search filter v-show="!loadingInfo" -->
    <search
      
      placeholder="Buscar candidato"
      @search="searchHandler"
    />

    <LoadingMsg v-if="loadingInfo" />
    <!-- No data <i class="far fa-clock"></i> -->
    <div v-if="!loadingInfo && candidates.length === 0">
      <p class="error-msg text-center" v-if="!isError">No hay información disponible</p>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontró información en Cartera de Candidatos!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
    </div>

    <slot v-if="!loadingInfo && candidates.length > 0">
      <!-- Results -->
      <div class="results">
        <div class="table-responsive">
          <table class="table b-table text-center">
            <thead>
              <tr>
                <th>Nombre</th>
                <th class="text-center">
                  <multiselect
                    v-if="filters.calificaciones"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.calificacion"
                    :options="filters.calificaciones"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Calificación
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_CORP']">
                  <multiselect
                    v-if="filters.areasInteres"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.areaInteres"
                    :options="filters.areasInteres"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                    @input="getSubAreas"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Área de interés
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_CORP']">
                  <slot v-if="filters.subAreasInteres.length === 0">Subárea</slot>
                  <slot v-if="filters.subAreasInteres.length > 0">
                    <multiselect
                      label="nombre"
                      track-by="nombre"
                      v-model="selectedFilters.subAreaInteres"
                      :options="filters.subAreasInteres"
                      :multiple="true"
                      :showLabels="false"
                      :searchable="false"
                      :close-on-select="true"
                    >
                      <template slot="selection">
                        <span class="multiselect__single">
                          Subárea
                          <i class="fas fa-sort"></i>
                        </span>
                      </template>
                    </multiselect>
                  </slot>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_CORP']">
                  <multiselect
                    v-if="filters.tiempoEnCartera"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.tiempoEnCartera"
                    :options="filters.tiempoEnCartera"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Tiempo En Cartera
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_COM']">
                  <multiselect
                    label="descripcion"
                    track-by="descripcion"
                    v-if="filters.edad"
                    v-model="selectedFilters.edad"
                    :options="filters.edad"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Edad
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_COM']">
                  <multiselect
                    v-if="filters.escolaridad"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.escolaridad"
                    :options="filters.escolaridad"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Escolaridad
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_COM']">
                  <multiselect
                    v-if="filters.ubicacion"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.ubicacion"
                    :options="filters.ubicacion"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Ubicación
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_ATN']">
                  <multiselect
                    label="descripcion"
                    track-by="descripcion"
                    v-if="filters.edad"
                    v-model="selectedFilters.edad"
                    :options="filters.edad"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Edad
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_ATN']">
                  <multiselect
                    v-if="filters.escolaridad"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.escolaridad"
                    :options="filters.escolaridad"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Escolaridad
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
                <th class="text-center" v-permission="['PORTOFOLIO_ATN']">
                  <multiselect
                    v-if="filters.ubicacion"
                    label="descripcion"
                    track-by="descripcion"
                    v-model="selectedFilters.ubicacion"
                    :options="filters.ubicacion"
                    :multiple="true"
                    :showLabels="false"
                    :searchable="false"
                    :close-on-select="true"
                  >
                    <template slot="selection">
                      <span class="multiselect__single">
                        Ubicación
                        <i class="fas fa-sort"></i>
                      </span>
                    </template>
                  </multiselect>
                </th>
              </tr>
            </thead>
            <tbody v-if="candidates.length === 0">
              <tr>
                <td colspan="6">
                  <div>No hay resultados</div>
                </td>
              </tr>
            </tbody>
            <tbody v-if="candidates.length > 0">
              <tr v-for="(candidate, key) in candidates" :key="key">
                <td>
                  <router-link
                    :to="{ name: 'prospect', params: { prospectId: candidate.idCandidato } }"
                  >{{ candidate.nombre }}</router-link>
                </td>
                <td
                  class="score"
                  :class="{ 'fit': candidate.scoreType === 'fit', 'not-fit': candidate.scoreType === 'not-fit' }"
                >
                  <i v-if="candidate.scoreType === 'fit'" class="fas fa-star"></i>
                  <i v-if="candidate.scoreType === 'not-fit'" class="fas fa-frown"></i>
                  {{ candidate.calificacion }}
                </td>
                <td v-permission="['PORTOFOLIO_COM']">{{ candidate.edad }}</td>
                <td v-permission="['PORTOFOLIO_COM']">{{ candidate.escolaridad }}</td>
                <td v-permission="['PORTOFOLIO_COM']">{{ candidate.ubicacion }}</td>
                <td v-permission="['PORTOFOLIO_ATN']">{{ candidate.edad }}</td>
                <td v-permission="['PORTOFOLIO_ATN']">{{ candidate.escolaridad }}</td>
                <td v-permission="['PORTOFOLIO_ATN']">{{ candidate.ubicacion }}</td>
                <td v-permission="['PORTOFOLIO_CORP']">{{ candidate.areaInteres }}</td>
                <td v-permission="['PORTOFOLIO_CORP']">{{ candidate.subAreaInteres }}</td>
                <td v-permission="['PORTOFOLIO_CORP']">{{ candidate.tiempoEnCartera }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </slot>

    <!-- Pagination -->
    <pagination-nav
      v-if="currentPage && totalPages > 1"
      :currentPage="currentPage"
      :totalPages="totalPages"
      @updateCurrentPage="init"
    ></pagination-nav>
  </div>
</template>

<style lang="scss" scoped>
@import "./List.scss";
</style>

<script>
import _ from 'lodash';
import portfolioSrv from '@/services/portfolio';
import Multiselect from 'vue-multiselect';
import Search from '@/components/Search/Search.vue';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    Multiselect,
    Search,
    SectionTitle,
    PaginationNav,
    LoadingMsg,
  },

  data() {
    return {
      branch: JSON.parse(sessionStorage.getItem('user')).branch,
      loadingInfo: true,
      search: '',
      filters: [],
      candidates: [],
      selectedFilters: {
        calificacion: null,
        areaInteres: null,
        subAreaInteres: null,
        tiempoEnCartera: null,
        edad: null,
        escolaridad: null,
        ubicacion: null,
      },
      totalPages: 0,
      currentPage: 0,
      isError: false,
    };
  },

  mounted() {
    this.getFilters().then(() => {
      this.init();
    });
  },

  methods: {
    init(page = 1) {
      const { selectedFilters } = this;
      const data = {
        modo: this.branch,
        paginaActual: page,
        ...selectedFilters,
      };

      if (this.search) data.busqueda = this.search;

      return this.getCandidates(data);
    },

    getCandidates(data) {
      this.loadingInfo = true;

      return portfolioSrv
        .searchCandidates(data)
        .then(({ entity }) => {
          const { paginaActual, totalPaginas, resultados } = entity;

          this.candidates = resultados;
          this.totalPages = totalPaginas;
          this.currentPage = paginaActual;
        })
        .catch(() => {
          this.isError = true;
          this.candidates = [];
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    getFilters() {
      return portfolioSrv
        .getFilters({ modo: this.branch })
        .then(({ entity }) => {
          this.filters = entity;
          this.filters.subAreasInteres = [];
        });
    },

    getBranch() {
      const [systemList] = JSON.parse(
        sessionStorage.getItem('user'),
      ).systemSections.filter(el => el.code === 'PORTOFOLIO');

      if (systemList) {
        return systemList.operations.filter(
          el => el === 'CORP' || el === 'COM' || el === 'ATN',
        )[0];
      }
    },

    getSubAreas() {
      this.filters.subAreasInteres = [];
      this.selectedFilters.subAreaInteres = null;

      return portfolioSrv
        .getSubAreas(this.selectedFilters.areaInteres.map(e => e.id))
        .then(({ entity }) => {
          if (this.selectedFilters.areaInteres.length > 0) {
            const list = entity.map(e => e.subareas);
            const subAreas = [].concat.apply([], [...list]);

            this.filters.subAreasInteres = subAreas;
            this.$forceUpdate();
          }
        });
    },

    searchHandler: _.debounce(function debounce(text) {
      this.search = text;
      this.resetPagination();
    }, 200),

    resetPagination() {
      this.currentPage = 0;
      this.init(1);
    },
  },

  watch: {
    selectedFilters: {
      deep: true,
      handler() {
        this.resetPagination();
      },
    },
  },
};
</script>

<template>
  <div>
    <div v-if="loadingReportFilters" class="loading">Obteniendo filtros...</div>
    <div v-if="!loadingReportFilters && !reportFilters" class="loading">No hay filtros disponibles</div>
    <slot v-if="!loadingReportFilters && reportFilters">
      <!-- Filters -->
      <div class="filters">
        <strong>Filtrar</strong>
        <b-row class="d-flex topics" v-permission="['METRICS_COM']">
          <b-col cols="12" md="4">
            <b-form-group label="División">
              <div class="select-wrapper">
                <b-form-select
                  name="idDivision"
                  v-model="filters.idDivision"
                  @change="getChilds($event, 'regionales', 'REGIONALES')"
                >
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.divisiones"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
          <b-col cols="12" md="4">
            <b-form-group label="Regional">
              <div class="select-wrapper">
                <b-form-select
                  name="idRegion"
                  v-model="filters.idRegion"
                  :disabled="!reportFilters.regionales"
                  @change="getChilds($event, 'gerencias', 'GERENCIAS')"
                >
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.regionales"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
          <b-col cols="12" md="4">
            <b-form-group label="Gerencia">
              <div class="select-wrapper">
                <b-form-select
                  name="idGerencia"
                  v-model="filters.idGerencia"
                  :disabled="!reportFilters.gerencias"
                >
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.gerencias"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>

          <b-col cols="12" md="4">
            <b-form-group label="Periodo">
              <div class="select-wrapper">
                <b-form-select name="idPeriodo" v-model="filters.idPeriodo">
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.periodo"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row class="d-flex topics" v-permission="['METRICS_CORP']">
          <b-col cols="12" md="4">
            <b-form-group label="Tipo">
              <div class="select-wrapper">
                <b-form-select
                  name="tipo"
                  v-model="filters.tipoVacante"
                  @change="cleanChilds('TIPO')"
                >
                  <option
                    v-for="item of reportFilters.tipoVacante"
                    :key="item.codigo"
                    :value="item.codigo"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
          <slot v-if="filters.tipoVacante === 'V_CORP'">
            <b-col cols="12" md="4">
              <b-form-group label="Área de Interés">
                <div class="select-wrapper">
                  <b-form-select
                    name="idAreaInteres"
                    v-model="filters.idAreaInteres"
                    @change="changeSubArea(filters.idAreaInteres, 'subAreaInteres', 'SUBAREAINTERES')"
                  >
                    <option :value="null">Selecciona una opción</option>
                    <option
                      v-for="item of reportFilters.areaInteres"
                      :key="item.idArea"
                      :value="item.idArea"
                    >{{ item.nombre }}</option>
                  </b-form-select>
                </div>
              </b-form-group>
            </b-col>
            <b-col cols="12" md="4">
              <b-form-group label="Subárea de Interés">
                <div class="select-wrapper">
                  <b-form-select
                    name="idSubAreaInteres"
                    v-model="filters.idSubAreaInteres"
                    :disabled="!reportFilters.subAreaInteres"
                  >
                    <option :value="null">Selecciona una opción</option>
                    <option
                      v-for="item of reportFilters.subAreaInteres"
                      :key="item.idSubArea"
                      :value="item.idSubArea"
                    >{{ item.nombre }}</option>
                  </b-form-select>
                </div>
              </b-form-group>
            </b-col>
          </slot>

          <b-col cols="12" md="4" v-if="filters.tipoVacante === 'V_CORP'">
            <b-form-group label="Nivel de puesto">
              <div class="select-wrapper">
                <b-form-select name="idNivelPuesto" v-model="filters.idNivelPuesto">
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.nivelPuesto"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
          <b-col cols="12" md="4">
            <b-form-group label="Periodo">
              <div class="select-wrapper">
                <b-form-select name="idPeriodo" v-model="filters.idPeriodo">
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.periodo"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>

          <b-col cols="12" md="4" v-if="filters.tipoVacante === 'V_CTS'">
            <b-form-group label="Centro costos">
              <div class="select-wrapper">
                <b-form-select name="centroCostos" v-model="filters.centroCostos">
                  <option :value="null">Selecciona una opción</option>
                  <option
                    v-for="item of reportFilters.centroCostos"
                    :key="item.id"
                    :value="item.id"
                  >{{ item.descripcion }}</option>
                </b-form-select>
              </div>
            </b-form-group>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="text-right">
            <b-button variant="primary" @click="updateChart()">Actualizar resultados</b-button>
          </b-col>
        </b-row>
      </div>
    </slot>
  </div>
</template>

<script>
import metricsSrv from '@/services/metrics';

export default {
  props: {
    updateChartInfo: Function,
  },

  data() {
    return {
      reportCode: this.$route.params.reportType,
      branch: JSON.parse(sessionStorage.getItem('user')).branch,
      loadingReportFilters: true,
      reportFilters: false,
      filters: {
        idDivision: null,
        idRegion: null,
        idGerencia: null,
        tipoVacante: 'V_CORP',
        idAreaInteres: null,
        idSubAreaInteres: null,
        centroCostos: null,
        idPeriodo: null,
        idNivelPuesto: null,
      },
    };
  },

  created() {
    this.getReportFilters();
  },

  methods: {
    getReportFilters() {
      const { reportCode, branch } = this;
      this.loadingReportFilters = true;

      return metricsSrv
        .getReportFilters({ reportCode, branch })
        .then(({ entity }) => {
          this.reportFilters = entity;
        })
        .finally(() => {
          this.loadingReportFilters = false;
        });
    },

    changeSubArea(id, nextFilter, filterName) {
      this.cleanChilds(filterName);

      if (id) {
        const [{ subareas }] = this.reportFilters.areaInteres.filter(
          el => el.idArea === id,
        );

        this.reportFilters = {
          ...this.reportFilters,
          subAreaInteres: [...subareas],
        };
      }
    },

    getChilds(evt, nextFilter, filterName) {
      this.cleanChilds(filterName);

      if (evt) {
        metricsSrv
          .getChildFilters({
            filtro: filterName,
            idElementoClave: evt,
          })
          .then(({ entity }) => {
            this.reportFilters[nextFilter] = entity;
          });
      }

      return false;
    },

    cleanChilds(filter) {
      let childs = [];

      switch (filter) {
        case 'REGIONALES':
          childs = [
            { field: 'idRegion', values: 'regionales' },
            { field: 'idGerencia', values: 'gerencias' },
          ];
          break;
        case 'GERENCIAS':
          childs = [{ field: 'idGerencia', values: 'gerencias' }];
          break;
        case 'SUBAREAINTERES':
          childs = [{ field: 'idSubAreaInteres', values: 'subAreaInteres' }];
          break;
        case 'TIPO':
          childs = [
            { field: 'idAreaInteres' },
            { field: 'idSubAreaInteres', values: 'subAreaInteres' },
            { field: 'centroCostos' },
          ];
          break;
        default:
          return true;
      }

      childs.forEach((i) => {
        this.filters[i.field] = null;
        if (i.values) this.reportFilters[i.values] = null;
      });

      return null;
    },

    updateChart() {
      this.$emit('updateChartInfo', this.filters);
    },
  },
};
</script>

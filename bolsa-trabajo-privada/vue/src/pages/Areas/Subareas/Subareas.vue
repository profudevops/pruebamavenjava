<template>
  <div class="subareas">
    <ConfimDeleteSubArea @deleteSubAreaModal="deleteSubAreaModal" @resetKey="resetKey" />

    <div class="header">
      <strong>Subáreas</strong>
      <span>Obligatorio</span>
    </div>
    <div class="content">
      <b-form novalidate @submit.prevent="addSubarea">
        <b-form-group label label-for="subarea">
          <b-form-input
            type="text"
            name="subarea"
            ref="subarea"
            class="input-field"
            placeholder="Subárea nueva"
            v-model="subarea"
          ></b-form-input>
        </b-form-group>
      </b-form>

      <div class="content-list">
        <p
          class="text-center text-message"
          v-if="!newSubareas.length && !currentSubareas.length"
        >El Área no cuenta actualmente con ninguna SubÁrea de interes.</p>
        <ul class="list" v-if="newSubareas.length">
          <li class="child" v-for="(child, key) in newSubareas" :key="key">
            <div class="without-edit" v-if="child.edit">
              <b-form novalidate @submit.prevent="editSubarea(key, child.copy, 'create')">
                <b-form-group label :label-for="`subarea-${key}`">
                  <b-form-input
                    type="text"
                    :name="`subarea-${key}`"
                    :ref="`subarea-${key}`"
                    class="input-field"
                    placeholder
                    v-model="child.copy"
                  ></b-form-input>
                </b-form-group>
                <div class="subactions">
                  <b-button type="submit" variant="link">Guardar</b-button>
                  <b-button variant="link" @click="closeEdition(key, 'create')">
                    <i class="fas fa-times"></i>
                  </b-button>
                </div>
              </b-form>
            </div>
            <div class="with-edit" v-if="!child.edit">
              <span>{{ child.nombre }}</span>
              <div class="actions">
                <a href="#" role="button" class="mr-3" @click="editChild(key, 'create')">
                  <i class="fas fa-pen"></i>
                </a>
                <a href="#" role="button" @click="deleteChild(key, 'create')">
                  <i class="fas fa-trash"></i>
                </a>
              </div>
            </div>
          </li>
        </ul>
        <ul class="list" v-if="currentSubareas.length">
          <li class="child" v-for="(child, key) in currentSubareas" :key="key">
            <div class="without-edit" v-if="child.edit">
              <b-form novalidate @submit.prevent="editSubarea(key, child.copy, 'edit')">
                <b-form-group label :label-for="`subarea-${key}`">
                  <b-form-input
                    type="text"
                    :name="`subarea-${key}`"
                    :ref="`subarea-${key}`"
                    class="input-field"
                    placeholder
                    v-model="child.copy"
                  ></b-form-input>
                </b-form-group>
                <div class="subactions">
                  <b-button type="submit" variant="link">Guardar</b-button>
                  <b-button variant="link" @click="closeEdition(key, 'edit')">
                    <i class="fas fa-times"></i>
                  </b-button>
                </div>
              </b-form>
            </div>
            <div class="with-edit" v-if="!child.edit">
              <span>{{ child.nombre }}</span>
              <div class="actions">
                <a href="#" role="button" class="mr-3" @click="editChild(key, 'edit')">
                  <i class="fas fa-pen"></i>
                </a>
                <a href="#" role="button" @click="deleteChild(key, 'edit')">
                  <i class="fas fa-trash"></i>
                </a>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Subareas.scss";
</style>

<script>
import ConfimDeleteSubArea from '@/components/Modal/ConfimDeleteSubArea.vue';
import areaReq from '@/services/areas.js';

export default {
  data() {
    return {
      subarea: '',
      newSubareas: [],
      currentSubareas: [],
    };
  },

  props: ['idArea', 'subareas'],

  components: {
    ConfimDeleteSubArea,
  },

  created() {
    this.currentSubareas = [...this.subareas];
  },

  methods: {
    addSubarea() {
      if (this.subarea) {
        this.newSubareas.unshift({ nombre: this.subarea });
        this.subarea = '';

        this.$emit('addSubarea', this.newSubareas);
      }
    },

    closeEdition(key, type = 'create') {
      if (type === 'create') {
        this.$set(this.newSubareas, key, {
          ...this.newSubareas[key],
          edit: false,
          copy: '',
        });
      } else {
        this.$set(this.currentSubareas, key, {
          ...this.currentSubareas[key],
          edit: false,
          copy: '',
        });
      }
    },

    editChild(key, type = 'create') {
      if (type === 'create') {
        this.$set(this.newSubareas, key, {
          ...this.newSubareas[key],
          edit: true,
          copy: this.newSubareas[key].nombre,
        });
      } else {
        this.$set(this.currentSubareas, key, {
          ...this.currentSubareas[key],
          edit: true,
          copy: this.currentSubareas[key].nombre,
        });
      }
    },

    editSubarea(key, value, type) {
      if (type === 'create') {
        this.$set(this.newSubareas, key, {
          ...this.newSubareas[key],
          edit: false,
          nombre: value,
          copy: '',
        });
        this.$emit('editSubarea', { key, value });
      } else {
        const { idArea, idSubArea } = this.currentSubareas[key];
        areaReq
          .editSubarea(idArea, idSubArea, value)
          .then(() => {
            this.$set(this.currentSubareas, key, {
              ...this.currentSubareas[key],
              edit: false,
              nombre: value,
              copy: '',
            });
          })
          .catch(() => {
            this.$notify({
              type: 'error',
              title: 'Error',
              text:
                'Ha ocurrido un error inesperado, por favor intentalo más tarde',
            });
          });
      }
    },

    deleteChild(key, type = 'create') {
      if (type === 'create') {
        this.newSubareas.splice(key, 1);
        this.$emit('deleteSubarea', key);
      } else {
        this.key = key;
        this.$bvModal.show('delete-subarea');
      }
    },

    resetKey() {
      this.key = null;
    },

    deleteSubAreaModal() {
      const [subArea] = this.currentSubareas.filter(
        (subarea, index) => this.key === index,
      );

      areaReq.deleteSubarea(subArea.idSubArea, this.idArea).then(() => {
        this.currentSubareas.splice(this.key, 1);
        this.key = null;
      });
    },
  },
};
</script>

<template>
  <div class="autosuggesst">
    <div class="auto-suggesst" :class="(emptyField && formSubmission) ? 'error': ''">
        <div class="fieldset__container">
          <b-form-input
            type="text"
            id="autocomplete"
            ref="autocomplete"
            name="autocomplete"
            v-model="textField"
            @blur="blurEvnt"
            @focus="focusEvnt"
            @input="inputEvnt"
          />
          <b-spinner v-if="isLoading" small></b-spinner>
          <i
            v-if="textField && !isLoading"
            class="clear-field fas fa-times"
            @click="clearField"
          ></i>
        </div>
        <div class="container__main" v-if="showList && results.length">
          <div class="container__list">
            <ul class="container__list--wrapper">
              <li
                v-for="(item, key) in results" :key="key"
                class="container__list--el"
                v-html="filterResult(item)"
                @click="clickEvnt(item)"
              >
              </li>
            </ul>
          </div>
        </div>
    </div>
    <div v-if="emptyField && formSubmission" class="invalid-feedback d-block">{{ fieldValidation }}</div>
  </div>
</template>

<style lang="scss" scoped>
@import "./AutoSuggest.scss";
</style>

<script>
import mainSrv from '@/services/main';
import _ from 'lodash';

export default {
  props: {
    currentValue: {
      type: Object,
      default: null,
    },
    emptyField: {
      type: Boolean,
      default: false,
    },
    path: String,
    queryStringParam: String,
    fieldValidation: String,
    formSubmission: Boolean,
  },

  data() {
    return {
      results: [],
      field: {},
      showList: false,
      isLoading: false,
      textField: '',
    };
  },

  created() {
    if (this.currentValue) {
      const { email: value } = this.currentValue;

      this.getResults(value)
        .then((results) => {
          const [el] = results.filter(el => el.email === value);

          this.textField = el.email;
          this.field = { ...el };
        });
    }
  },

  methods: {
    filterResult(item) {
      if (this.textField) {
        const r = new RegExp(this.textField, 'g');

        return item.email.replace(r, `<b>${this.textField}</b>`);
      }

      return item.email;
    },

    blurEvnt(e) {
      const $elOutside = e.relatedTarget;
      let isClicked = false;
      if ($elOutside) {
        const [$children] = e.relatedTarget.children;

        if (!$children) {
          this.showList = false;
        } else {
          isClicked = true;
        }
      } else {
        this.showList = false;
      }

      if (this.textField !== this.field.email && !isClicked) {
        this.textField = this.field.email;
      }
    },

    clickEvnt(value) {
      this.showList = false;
      this.textField = value.email;
      this.field = { ...value };
      this.$emit('setFieldfValue', value);
    },

    focusEvnt() {
      this.showList = true;
    },

    inputEvnt: _.debounce(function (value) {
      this.getResults(value);
    }, 500),

    clearField() {
      setTimeout(() => {
        this.textField = '';
        this.field = {};
        this.results = [];
        this.$emit('setFieldfValue', this.field);
      }, 100);
    },

    getResults(value) {
      return new Promise((resolve, reject) => {
        this.results = [];
        this.isLoading = true;

        mainSrv
          .getResultsFromPath(this.path, value, this.queryStringParam)
          .then((res) => {
            this.results = [...res.data.entity];
            resolve(this.results);
          })
          .catch((error) => {
            reject();
          })
          .finally(() => {
            this.isLoading = false;
          });
      });
    },
  },
};
</script>

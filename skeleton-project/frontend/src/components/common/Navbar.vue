<template>
  <div class="navigationBar">
    <b-collapse id="collapse_navbar" v-if="constants.IS_LOGED_IN">
      <b-navbar id="navigationBar" type="ligth" variant="#ffcabd">
        <b-navbar-nav class="ml-auto mr-auto">
          <b-nav-item class="mr-5" href="#">
            <router-link :to="{ name: constants.URL_TYPE.POST.MANAGEEXPERIENCE}">📝경험 관리</router-link>
          </b-nav-item>
          <b-nav-item class="mr-5">
            <router-link :to="{ name: constants.URL_TYPE.POST.MANAGEPORTFOLIO }">📇포트폴리오 관리</router-link>
          </b-nav-item>
          <b-nav-item class="mr-5" href="#">💼지원 목록</b-nav-item>
          <b-nav-item class="mr-5" href="#">👥커뮤니티</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
    </b-collapse>
    <b-icon-arrow-up-circle-fill
      class="navBtn"
      v-b-toggle.collapse_navbar
      v-if="constants.IS_LOGED_IN"
    ></b-icon-arrow-up-circle-fill>
  </div>
</template>

<script>
import constants from "../../lib/constants";
export default {
  data: function () {
    return {
      constants,
    };
  },
};
</script>

<style>
#navigationBar {
  border-bottom-right-radius: 4px;
  background-color: #ffcabd;
}
.navigationBar {
  position: fixed;
  margin: 0 auto;
  left: 0;
  right: 0;
  width: 60%;
}
.navBtn:hover {
  transform: scale(1.2, 1.2);
}
/* .show.navBtn {
  transform: rotate(180deg);
} */
</style>

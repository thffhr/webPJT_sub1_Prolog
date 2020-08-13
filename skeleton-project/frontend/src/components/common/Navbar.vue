<template>
  <div id="navigationBar">
    <b-collapse id="collapse_navbar" v-if="constants.IS_LOGED_IN">
      <b-navbar id="pageMenue">
        <b-navbar-nav class="ml-auto mr-auto">
          <b-nav-item class="mr-5" href="#">
            <router-link
              :to="{ name: constants.URL_TYPE.POST.MANAGEEXPERIENCE }"
              >📝경험 관리</router-link
            >
          </b-nav-item>
          <b-nav-item class="mr-5">
            <router-link :to="{ name: constants.URL_TYPE.POST.MANAGEPORTFOLIO }"
              >📇포트폴리오 관리</router-link
            >
          </b-nav-item>
          <b-nav-item class="mr-5" href="#">
            <router-link :to="{ name: constants.URL_TYPE.POST.MANAGEAPPLY }"
              >💼지원 목록</router-link
            >
          </b-nav-item>
          <!-- <b-nav-item class="mr-5" href="#">👥커뮤니티</b-nav-item> -->
        </b-navbar-nav>
      </b-navbar>
    </b-collapse>
    <b-icon-arrow-up-circle-fill
      id="navBtn"
      v-b-toggle.collapse_navbar
      v-if="constants.IS_LOGED_IN"
    ></b-icon-arrow-up-circle-fill>
  </div>
</template>

<script>
import constants from "../../lib/constants";
export default {
  data: function() {
    return {
      constants,
    };
  },
};
</script>

<style>
a:link {
  color: white;
}
a:visited {
  color: white;
}
#navigationBar {
  position: fixed;
  margin: 0 auto;
  top: 0;
  /* right: auto; */
  left: 50%;
  transform: translate(-50%, 0%);
  width: 60%;
  border: 0;
  z-index: 10;
}
#pageMenue {
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
  background-color: #7a63ff;
  color: white;
}
#navBtn {
  position: relative;
  top: 2px;
  left: 50%;
  transform: translate(-50%, 0%);
}
.navBtn:hover {
  transform: scale(1.2, 1.2);
}
</style>

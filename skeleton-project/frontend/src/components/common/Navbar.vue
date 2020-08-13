<template>
  <div id="navigationBar">
    <b-collapse id="collapse_navbar" v-if="constants.IS_LOGED_IN">
      <b-navbar id="pageMenue">
        <b-navbar-nav class="ml-auto mr-auto">
          <b-nav-item class="mr-5" href="#">
            <router-link
              id="rlink"
              :to="{ name: constants.URL_TYPE.POST.MANAGEEXPERIENCE }"
              >📝경험 관리</router-link
            >
          </b-nav-item>
          <b-nav-item class="mr-5">
            <router-link
              id="rlink"
              :to="{ name: constants.URL_TYPE.POST.MANAGEPORTFOLIO }"
              >📇포트폴리오 관리</router-link
            >
          </b-nav-item>
          <b-nav-item class="mr-5" href="#">
            <router-link
              id="rlink"
              :to="{ name: constants.URL_TYPE.POST.MANAGEAPPLY }"
              >💼지원 목록</router-link
            >
          </b-nav-item>
          <!-- <b-nav-item class="mr-5" href="#">👥커뮤니티</b-nav-item> -->
        </b-navbar-nav>
      </b-navbar>
    </b-collapse>
    <div id="navBtn" v-if="constants.IS_LOGED_IN" v-b-toggle.collapse_navbar>
      <!-- <b-icon-chevron-compact-down class="mr-auto ml-auto"> -->
      </b-icon-chevron-compact-down>
    </div>
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
  /* border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px; */
  background-color: #7a63ff;
  /* color: white;
  text-decoration-color: white; */
}
#rlink {
  color: white;
  text-decoration: none;
}
#navBtn {
  padding: 0px 20px;
  margin: 0 auto;
  width: 100%;
  height: 10px;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px;
  background-color: #7a63ff;
  color: white;
}
/* .collapsed {
  transform: rotate(0deg);
}
.not-collapsed {
  transform: rotate(180deg);
} */
</style>

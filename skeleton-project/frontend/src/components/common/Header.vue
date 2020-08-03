<template>
  <div id="header" v-if="isHeader && constants.IS_LOGED_IN">
    <h1 id="blog_name">
      <!-- 여기도 로그인시랑 비로그인시 다르게 -->
      <router-link
        v-if="!constants.IS_LOGED_IN"
        v-bind:to="{ name: constants.URL_TYPE.MAIN.NOLOGINHOME }"
      >
        <!-- 로그인 안하면 header에 로고 없고 대신 하단(메인)에 크게 삽입 -->
      </router-link>
      <router-link v-else v-bind:to="{ name: constants.URL_TYPE.MAIN.LOGINHOME }">
        <!-- 로고 이미지 삽입 -->
        <b-img :src="require(`@/assets/img/top_logo.jpg`)" width="150"></b-img>
      </router-link>
    </h1>
    <div class="right" v-if="constants.IS_LOGED_IN">
      <b-dropdown id="dropdown-1" variant="text" toggle-class="text-decoration-none" class="m-md-2">
        <template v-slot:button-content>
          <!-- 사용자의 프로필사진 url 첨부 -->
          <img
            class="HeaderImage mr-2"
            ref="HeaderImage"
            src="https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-512.png"
            style="width: 2rem; height: 2rem;"
          />
          <div id="small_profile" :uid="uid">{{ uid }}</div>님, 환영합니다.
          <!-- <span class="sr-only">Search</span> -->
        </template>

        <b-dropdown-item href="#" @click="logout">로그아웃</b-dropdown-item>
        <b-dropdown-item href="#" @click="$bvModal.show('confirmPassword')">
          회원정보 수정
          <b-modal id="confirmPassword" hide-footer>
            <template v-slot:modal-title>비밀번호 재확인</template>
            <div class="d-block text-center">
              <ConfirmPassword />
            </div>
          </b-modal>
        </b-dropdown-item>
      </b-dropdown>
    </div>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import ConfirmPassword from "../../page/user/ConfirmPassword.vue";
import axios from "axios";

export default {
  name: "Header",
  components: { ConfirmPassword },
  props: ["isHeader"],
  computed: {},
  watch: {},
  created() {
    let HeaderImage = this.$refs.HeaderImage; //img dom 접근

    axios
      .get(this.$SERVER_URL + `/account/ckprofile/${localStorage["uid"]}`)
      .then((response) => {
        if (response.data.status) {
          console.log(response);
          if (response.data.object != null) {
            console.log("등록된 프로필 이미지가 있습니다.");
            HeaderImage.src =
              this.$SERVER_URL + `/account/profile/${localStorage["uid"]}`;
          } else {
            console.log("등록된 프로필 이미지가 없습니다.");
          }
        } else {
          console.log("프로필 이미지 가져오는 중 에러 발생");
          console.log(response);
        }
      });
  },
  data: function () {
    return {
      constants,
      uid: localStorage["uid"],
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("uid");
      localStorage.removeItem("password");
      localStorage.removeItem("email");
      localStorage.removeItem("createDate");
      constants.IS_LOGED_IN = false;
      this.$router.push({ name: constants.URL_TYPE.MAIN.NOLOGINHOME });
    },
  },
};
</script>

<style>
#header {
  padding: 0px;
  background-color: #eee;
}
#blog_name {
  padding: 14px 40px;
}
#small_profile {
  display: inline;
}
.HeaderImage {
  border-radius: 50%;
  border-color: #ffcabd;
  background-color: white;
}
</style>

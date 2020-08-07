<template>
  <div class="nologinHome">
    <b-container>
      <b-row align-v="center">
        <b-col cols="12" lg="6">
          <b-img :src="require(`@/assets/img/logo.jpg`)" contain width="538" class="mt-4 mb-1"></b-img>
          <div class="m-5">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Placeat
            perspiciatis suscipit quidem illum nihil quas perferendis omnis,
            dignissimos ab recusandae sunt aspernatur maiores architecto dolor
            at similique animi facere incidunt!
          </div>
        </b-col>
        <!-- <div class="w-100"></div> -->
        <b-col cols="12" lg="6">
          <div class="custom-login-style">
            <b-form-input
              v-model="emailOrUid"
              id="emailOrUid"
              placeholder="이메일 혹은 아이디를 입력해주세요"
              type="text"
              style="width: 60%;"
            ></b-form-input>

            <b-form-input
              @keyup.enter="login"
              v-model="password"
              type="password"
              placeholder="영문, 숫자 혼용 8자 이상"
              style="width: 60%;"
            ></b-form-input>
          </div>
          <div style="text-align: center;" class="mt-3">
            <b-button class="mr-2" @click="login">로그인</b-button>
            <router-link :to="{ name: constants.URL_TYPE.USER.JOIN }">
              <b-button>회원가입</b-button>
            </router-link>
          </div>
          <br />
          <div style="text-align: center;">
            <span
              class="findUser text-secondary"
              @click="$bvModal.show('findUserModal')"
              style="text-align: center; cursor: pointer;"
            >아이디 또는 비밀번호를 잊으셨나요?</span>
          </div>
          <b-modal id="findUserModal" hide-footer>
            <template v-slot:modal-title>아이디 찾기</template>
            <div class="d-block text-center">
              <FindUserByEmail />
            </div>
          </b-modal>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";

import FindUserByEmail from "../../page/user/FindUserByEmail.vue";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "NoLoginHome",
  components: { FindUserByEmail },
  props: [],
  computed: {},
  watch: {},
  created() {
    if (constants.IS_LOGED_IN) {
      this.$router.push({ name: constants.URL_TYPE.MAIN.LOGINHOME });
    }
  },
  data: () => {
    return {
      constants,
      emailOrUid: "",
      password: "",
      isLogedin: false,
      // images: {
      //   logo:
      // },
    };
  },
  methods: {
    login() {
      axios
        .get(this.$SERVER_URL + "/account/login/", {
          params: { emailOrUid: this.emailOrUid, password: this.password },
        })
        .then((response) => {
          console.log(response);
          if (response.data.data == "success") {
            localStorage.setItem("uid", response.data.object.uid);
            localStorage.setItem("nickname", response.data.object.nickname);
            localStorage.setItem("email", response.data.object.email);
            localStorage.setItem("password", response.data.object.password);
            constants.IS_LOGED_IN = true;
            // alert("로그인 되었습니다!");
            location.href = "http://localhost:3000/#/logedin";
            // this.$router.go({ name: constants.URL_TYPE.MAIN.LOGINHOME });
          } else {
            alert("이메일 또는 비밀번호가 잘못되었습니다.");
          }
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },
};
</script>

<style>
.nologinHome {
  margin-top: 50px;
}
#findUser:hover {
  cursor: pointer;
  text-align: center;
}
.custom-login-style {
  text-align: -webkit-center;
}
</style>

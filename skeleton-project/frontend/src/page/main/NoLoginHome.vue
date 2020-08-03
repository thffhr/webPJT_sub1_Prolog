<template>
  <div>
    <div class="user">
      <b-container class="mt-5">
        <b-row align-v="start">
          <b-col align-self="stretch" class="m-5">
            <!-- 로고 -->
            <b-img :src="require(`@/assets/img/logo.jpg`)" contain width="538" class="mt-4 mb-1"></b-img>
            <!-- 설명 -->
            <!-- <p id="mainptag"> -->
            <b-col class="m-5">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Placeat
              perspiciatis suscipit quidem illum nihil quas perferendis omnis,
              dignissimos ab recusandae sunt aspernatur maiores architecto dolor
              at similique animi facere incidunt!
              <!-- </p> -->
            </b-col>
          </b-col>
          <b-col class="m-5" align-v="end" align-self="stretch">
            <b-card class="card mt-5">
              <b-card-text>
                <!-- 정렬 수정 필요__가운데 정렬 -->
                <b-container>
                  <b-row class="my-1" align-h="center" align-v="end">
                    <b-col role="group" cols="9">
                      <div class="input-wrap ml-auto mr-auto">
                        <input
                          v-model="emailOrUid"
                          id="emailOrUid"
                          placeholder="이메일 혹은 아이디를 입력해주세요"
                          type="text"
                        />
                      </div>
                    </b-col>
                  </b-row>
                  <b-row class="my-1" align-h="center">
                    <b-col role="group" cols="9">
                      <div class="input-wrap ml-auto mr-auto">
                        <input
                          @keyup.enter="login"
                          v-model="password"
                          type="password"
                          id="password"
                          placeholder="영문, 숫자 혼용 8자 이상"
                        />
                      </div>
                    </b-col>
                  </b-row>
                  <b-row class="my-1" align-h="center">
                    <b-col role="group" class="password-wrap" cols="9">
                      <!-- 버튼 색상 통일 __ 아마 vbind때문에 차이 나는듯 -->
                      <b-button class="mr-2" @click="login">로그인</b-button>
                      <router-link :to="{ name: constants.URL_TYPE.USER.JOIN }">
                        <b-button>회원가입</b-button>
                      </router-link>
                    </b-col>
                  </b-row>
                  <b-row class="my-1" align-h="center">
                    <b-col role="group" class="password-wrap" cols="9"></b-col>
                  </b-row>
                </b-container>
              </b-card-text>
            </b-card>
            <p
              class="btn btn--back btn--login text-secondary"
              @click="$bvModal.show('bv-modal-example1')"
            >아이디 또는 비밀번호를 잊으셨나요?</p>
            <b-modal id="bv-modal-example1" hide-footer>
              <template v-slot:modal-title>아이디 찾기</template>
              <div class="d-block text-center">
                <FindUserByEmail />
              </div>
              <!-- <b-button
                id="closeBtn"
                class="mt-3"
                block
                @click="$bvModal.hide('bv-modal-example1')"
                >Close Me</b-button
              >-->
            </b-modal>
          </b-col>
        </b-row>
      </b-container>
    </div>
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
            //   console.log("!!!!!!!!!!!!!!!")
            // document.getElementById("closeBtn").click();
            localStorage.setItem("uid", response.data.object.uid);
            localStorage.setItem("password", response.data.object.password);
            localStorage.setItem("email", response.data.object.email);
            localStorage.setItem("createDate", response.data.object.createDate);
            constants.IS_LOGED_IN = true;
            alert("로그인 되었습니다!");
            // this.email = "";
            // this.password = "";
            this.$router.push({ name: constants.URL_TYPE.MAIN.LOGINHOME });
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
.mainptag {
  width: 538 !important;
}
</style>

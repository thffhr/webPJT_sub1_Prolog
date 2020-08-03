<template>
  <div class="ml-auto mr-auto">
    <!-- <h1>이메일로유저찾기!</h1> -->
    <b-form-input
      v-model="email"
      id="email-tofind"
      placeholder="이메일을 입력해주세요"
      @keyup.enter="FindUserByEmail"
      type="text"
    ></b-form-input>

    <button class="btn btn--back btn--login" @click="FindUid">아이디 찾기</button>
    <button class="btn btn--back btn--login" @click="FindPassword">비밀번호 찾기</button>
  </div>
</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "FindUserByEmail",
  components: {},
  created() {},
  watch: {},
  data: () => {
    return {
      constants,
      email: "",
      props: [],
    };
  },
  methods: {
    FindUid() {
      axios
        .get(this.$SERVER_URL + "/account/findUserByEmail/", {
          params: { email: this.email },
        })
        .then((response) => {
          if (response.data.data === "이메일로 유저정보를 가져왔습니다") {
            // console.log(response.data.object.uid);
            const userId = response.data.object.uid;
            const userPassword = response.data.object.password;
            const userEmail = response.data.object.email;
            axios
              .put(this.$SERVER_URL + "/email/sendInfo", {
                subject: "아이디찾기 이메일",
                text: userId,
                toEmail: userEmail,
              })
              .then((response) => {
                console.log(response);
                alert("아이디 찾기 이메일 발송");
              })
              .catch((error) => {
                alert("아이디찾기 이메일 보내기 실패");
                console.log(error);
              });
          } else {
            alert("이메일이 잘못되었을껄?");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    FindPassword() {
      axios
        .get(this.$SERVER_URL + "/account/findUserByEmail/", {
          params: { email: this.email },
        })
        .then((response) => {
          if (response.data.data === "이메일로 유저정보를 가져왔습니다") {
            // console.log(response.data.object.uid);
            const userId = response.data.object.uid;
            const userPassword = response.data.object.password;
            const userEmail = response.data.object.email;
            axios
              .put(this.$SERVER_URL + "/email/sendInfo", {
                subject: "비밀번호찾기 이메일",
                text: userPassword,
                toEmail: userEmail,
              })
              .then((response) => {
                // console.log(response);
                alert("비밀번호 찾기 이메일 발송");
              })
              .catch((error) => {
                console.log(error);
                alert("비밀번호 찾기 이메일 보내기 실패");
              });
          } else {
            alert("이메일이 잘못되었을껄?");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
#email-tofind {
  margin: 20px auto;
  width: 300px;
}
</style>

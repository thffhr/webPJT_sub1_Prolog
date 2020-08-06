<template>
  <div id="join">
    <b-card class="join_card" header-tag="header" footer-tag="footer">
      <template v-slot:header>
        <h4 class="mb-0 mt-2 mb-2">회원가입</h4>
        <!-- 사용자 닉네임 잘 들어가는지 확인하기 -->
        <p class="mb-2">회원 정보를 입력해주세요.</p>
      </template>
      <b-card-text>
        <b-container class="form-wrap">
          <b-row class="my-1">
            <b-col role="group" cols="9">
              <b-form-text id="email-help">아이디, 닉네임, 이메일 변경 시 중복확인이 필요합니다.</b-form-text>
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col role="group" cols="9">
              <b-form-input
                id="uid"
                v-model="uid"
                :state="uidState"
                aria-describedby="uid-help uid-feedback"
                placeholder="아이디를 입력해주세요"
                trim
                type="text"
              ></b-form-input>
              <b-form-invalid-feedback id="uid-feedback">{{uidFeedback}}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col role="group" cols="9">
              <b-form-input
                id="nickName"
                v-model="nickName"
                :state="nickNameState"
                aria-describedby="nickName-help nickName-feedback"
                placeholder="닉네임을 입력해주세요"
                trim
                type="text"
              ></b-form-input>
              <b-form-invalid-feedback id="nickName-feedback">{{nicknameFeedback}}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col role="group" cols="9">
              <b-form-input
                id="email"
                v-model="email"
                :state="emailState"
                aria-describedby="email-help email-feedback"
                placeholder="이메일을 입력해주세요"
                trim
                type="text"
              ></b-form-input>
              <b-form-invalid-feedback id="email-feedback">{{emailFeedback}}</b-form-invalid-feedback>
            </b-col>
            <b-col class="align-self-left">
              <span @click="emailcerti" class="emailcerti">이메일 인증</span>
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col role="group" class="password-wrap" cols="9">
              <b-form-input
                id="password"
                v-model="password"
                placeholder="비밀번호를 입력해주세요"
                :state="passwordState"
                aria-describedby="password-help password-feedback"
                trim
                :type="passwordType"
              ></b-form-input>
              <span :class="{ active: passwordType === 'text' }">
                <i class="fas fa-eye"></i>
              </span>
              <b-form-invalid-feedback id="password-feedback">{{passwordFeedback}}</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <b-row class="my-1">
            <b-col role="group" class="password-wrap" cols="9">
              <b-form-input
                id="password-confirm"
                v-model="passwordConfirm"
                :state="passwordConfirmState"
                placeholder="비밀번호를 한번 더 입력해주세요"
                trim
                :type="passwordConfirmType"
              ></b-form-input>
              <span :class="{ active: passwordConfirmType === 'text' }">
                <i class="fas fa-eye"></i>
              </span>
              <b-form-invalid-feedback id="password-feedback">비밀번호를 정확히 입력해주세요.</b-form-invalid-feedback>
            </b-col>
          </b-row>
          <!-- <b-row>
              <b-col cols="9">
                <label>
                  <input v-model="isTerm" type="checkbox" id="term" />
                  <span>약관에 동의합니다</span>
                </label>
                <span class="go-term">약관 보기</span>
              </b-col>
          </b-row>-->
        </b-container>
      </b-card-text>
      <div class="ml-auto">
        <b-button @click="join" href="#" class="ml-2 mr-2">회원가입</b-button>
        <router-link :to="{ name: constants.URL_TYPE.MAIN.NOLOGINHOME }">
          <b-button>취소</b-button>
        </router-link>
      </div>
      <!-- <em>Footer Slot</em> -->
    </b-card>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import axios from "axios";

export default {
  components: {},
  created() {},
  watch: {},
  data: () => {
    return {
      constants,
      email: "",
      uid: "",
      nickName: "",
      password: "",
      passwordConfirm: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      uidFeedback: "",
      nicknameFeedback: "",
      emailFeedback: "",
      passwordFeedback: "",
      uidDupcheck: true,
      nickDupcheck: true,
      emailDupcheck: true,
      passwordDupcheck: true,
    };
  },
  computed: {
    uidState() {
      if (this.uid.length > 0 ? true : false) {
        let form = new FormData();
        form.append("uid", this.uid);
        axios
          .post(this.$SERVER_URL + "/account/signup/idcheck", form)
          .then((response) => {
            this.uidDupcheck = response.data.status;
          })
          .catch((error) => {
            console.log(error.response);
          });
        if (this.uidDupcheck) {
          return true;
        } else {
          this.uidFeedback = "아이디가 중복되었습니다.";
          return false;
        }
      } else {
        this.uidFeedback = "필수 입력값입니다.";
        return false;
      }
    },
    nickNameState() {
      if (this.nickName.length > 0 ? true : false) {
        let form = new FormData();
        form.append("nickname", this.nickName);
        axios
          .post(this.$SERVER_URL + "/account/signup/nicknamecheck", form)
          .then((response) => {
            this.nickDupcheck = response.data.status;
          })
          .catch((error) => {
            console.log(error.response);
          });
        if (this.nickDupcheck) {
          return true;
        } else {
          this.nicknameFeedback = "닉네임이 중복되었습니다.";
          return false;
        }
      } else {
        this.nicknameFeedback = "필수 입력값입니다.";
        return false;
      }
    },
    emailState() {
      if (
        this.email.indexOf("@") < 0 ||
        this.email.indexOf("@") >= this.email.indexOf(".com")
      ) {
        this.emailFeedback = "이메일 형식이 맞지 않습니다.";
        return false;
      } else {
        let form = new FormData();
        form.append("email", this.email);
        axios
          .post(this.$SERVER_URL + "/account/signup/emailcheck", form)
          .then((response) => {
            this.emailDupcheck = response.data.status;
          })
          .catch((error) => {
            console.log(error.response);
          });
        if (this.emailDupcheck) {
          return true;
        } else {
          this.emailFeedback = "이메일이 중복되었습니다.";
          return false;
        }
      }
    },
    passwordState() {
      if (this.password.length > 0) {
        var pattern1 = /[0-9]/;
        var pattern2 = /[a-zA-Z]/;
        if (
          !pattern1.test(this.password) ||
          !pattern2.test(this.password) ||
          this.password.length < 8
        ) {
          this.passwordFeedback =
            "비밀번호는 8자리 이상 문자, 숫자로 작성해주세요.";
          return false;
        } else {
          return true;
        }
      } else {
        this.passwordFeedback = "필수 입력값입니다.";
        return false;
      }
    },
    passwordConfirmState() {
      if (this.password) {
        if (this.password === this.passwordConfirm) {
          return true;
        } else {
          return false;
        }
      }
    },
  },
  methods: {
    // 이메일 인증 메서드 만들기
    emailcerti() {
      axios
        .put(this.$SERVER_URL + "/email/auth", {
          subject: "블로그 가입 인증 메일입니다.",
          toEmail: this.email,
        })
        .then((response) => {
          console.log(response);
          alert("회원가입 인증 메일이 발송되었습니다. 메일을 확인해 주세요.");
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
    join() {
      if (
        this.uidDupcheck &&
        this.nickDupcheck &&
        this.emailDupcheck &&
        this.password
      ) {
        axios
          .delete(this.$SERVER_URL + `/email/${this.email}`, this.email)
          .then((response) => {
            if (response.data.status === 500) {
              let form = new FormData();
              axios
                .post(this.$SERVER_URL + `/account`, {
                  uid: this.uid,
                  email: this.email,
                  password: this.password,
                  nickname: this.nickName,
                })
                .then((response) => {
                  if (response.data.status == true) {
                    alert("회원가입이 완료되었습니다. 로그인을 해 주세요.");
                    this.$router.push({ path: "/" });
                  }
                })
                .catch((error) => {
                  alert("회원가입 실패하였습니다. 다시 시도 해 주세요.");
                });
            }
          })
          .catch((error) => {
            console.log(error.response);
            alert("이메일 인증이 완료되지 않았습니다.");
          });
      }
    },
  },
};
</script>

<style>
#join {
  width: 900px;
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.emailcerti:hover {
  cursor: pointer;
  opacity: 0.5;
}
</style>


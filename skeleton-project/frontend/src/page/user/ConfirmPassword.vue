<template>
  <div class="card-card">
    <div class="card-body">
      <b-row class="my-1">
        <b-col class="mr-auto ml-auto" role="group" cols="9">
          <div class="confirmInput">
            <div @keyup.enter="$bvModal.hide('confirmPassword')">
              <b-form-input
                v-model="password"
                type="password"
                id="password"
                @keyup.enter="confirmPassword"
                placeholder="영문, 숫자 혼용 8자 이상"
              ></b-form-input>
            </div>
          </div>
        </b-col>
      </b-row>
      <div @click="$bvModal.hide('confirmPassword')">
        <b-button class="mt-1" @click="confirmPassword">확인</b-button>
        <!-- <button class="btn btn--back btn--login" @click="confirmPassword">확인</button> -->
      </div>
    </div>
  </div>
</template>

<script>
import constants from "../../lib/constants.js";

export default {
  name: "ConfirmPassword",
  data() {
    return {
      constants,
      password: "",
    };
  },
  created() {},
  methods: {
    confirmPassword(password) {
      if (this.password === localStorage["password"]) {
        // document.getElementById("closeBtn").click();
        // localStorage.setItem("double-checked", "Y");
        this.$router.push({ name: constants.URL_TYPE.USER.UPDATE });
        constants.IS_CONFIRMED_PASSWORD = true;
      } else {
        alert("비밀번호가 틀렸습니다.");
      }
    },
  },
};
</script>

<style>
.confirmInput {
  margin: 0 auto;
}
#password {
  margin: 0 auto;
}
</style>

import { Component, Vue } from "vue-property-decorator";

import AuthApi from "@/api/AuthApi";
import atmStore from "@/store/atmStore";

@Component({})
export default class LoginPage extends Vue {
  accountNumber: string = "";
  pin: string = "";

  mounted(): void {}

  async login() {
    await atmStore.setAccountNumber(this.accountNumber);
    AuthApi.getAuthApiInstance().login(this.accountNumber, this.pin);
  }
}

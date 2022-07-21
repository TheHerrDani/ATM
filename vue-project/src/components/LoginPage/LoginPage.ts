import { Component, Vue } from "vue-property-decorator";

import AuthApi from "@/api/AuthApi";
import atmStore from "@/store/atmStore";
import TokenStore from "@/store/TokenStore";

@Component({})
export default class LoginPage extends Vue {
  accountNumber: string = "";
  pin: string = "";

  accountNrError = "";
  pinError = "";

  get backendLoginError(): string {
    return TokenStore.getLoginError;
  }

  get validateAccountNr(): boolean {
    const accountNrValidation = this.accountNumber.match("^[0-9]{9}$");
    return accountNrValidation !== null && accountNrValidation.length > 0;
  }

  get validatePin(): boolean {
    const pinValidation = this.pin.match("^[0-9]{4}$");
    return pinValidation !== null && pinValidation.length > 0;
  }

  async login() {
    if (this.validateAccountNr && this.validatePin) {
      await atmStore.setAccountNumber(this.accountNumber);
      AuthApi.getAuthApiInstance().login(this.accountNumber, this.pin);
    }

    this.validateAccountNr
      ? (this.accountNrError = "")
      : (this.accountNrError =
          "The Account Number must be a nine decimal number");
    this.validatePin
      ? (this.pinError = "")
      : (this.pinError = "The PIN number must be a four decimal number");
  }
}

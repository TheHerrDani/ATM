import { Component, Vue } from "vue-property-decorator";
import EventBus from "@/eventBus";

import AtmStore from "../../store/atmStore";
import CheckModalStore from "../../store/checkModalStore";
import { Menu } from "@/types";
import AccountApi from "@/api/AccountApi";
import atmStore from "../../store/atmStore";

@Component({})
export default class Process extends Vue {
  notes = [5, 10, 20, 50, 100, 200, 500];
  selectedNote = 0;
  menuEnum = Menu;
  uncheckedCustomAmount = "";

  get atmMoney() {
    return "€ " + atmStore.getAtmStateInformation.atmAllMoney;
  }

  get euroFiftyCount() {
    return atmStore.getAtmStateInformation.euroFiftyCount;
  }

  get euroTwentyCount() {
    return atmStore.getAtmStateInformation.euroTwentyCount;
  }

  get euroTenCount() {
    return atmStore.getAtmStateInformation.euroTenCount;
  }

  get euroFiveCount() {
    return atmStore.getAtmStateInformation.euroFiveCount;
  }

  get isError() {
    return CheckModalStore.isErrorTrue;
  }

  get errorMsg() {
    return CheckModalStore.getErrorMessage;
  }

  get isSuccess() {
    return CheckModalStore.isSuccessTrue;
  }

  get selectedMenu() {
    return AtmStore.getSelectedMenu;
  }

  get withdrawalAmount() {
    return AtmStore.getWithdrawalAmount;
  }

  mounted(): void {
    EventBus.$on("okBtnClicked", () => {
      console.log(this.selectedNote);
      if (this.uncheckedCustomAmount === "" && this.selectedNote === 0) {
        CheckModalStore.setIsError(true);
        CheckModalStore.setErrorMessage(
          "There is no selected amount to withdraw."
        );
        return;
      }

      if (this.uncheckedCustomAmount !== "") {
        let requestedMoney = parseInt(this.uncheckedCustomAmount);
        if (requestedMoney % 5 !== 0) {
          CheckModalStore.setIsError(true);
          CheckModalStore.setErrorMessage(
            "The given amount is not a multiple of 5."
          );
          CheckModalStore.setIsSuccess(false);
        } else {
          AtmStore.setWithdrawalAmount(this.uncheckedCustomAmount);
          AccountApi.getAccountApiInstance().dispenseMoney(
            AtmStore.getAccountInformation.accountNumber,
            requestedMoney
          );
        }
      } else {
        AtmStore.setWithdrawalAmount(this.selectedNote.toString());
        if (this.selectedNote !== 0) {
          AccountApi.getAccountApiInstance().dispenseMoney(
            AtmStore.getAccountInformation.accountNumber,
            this.selectedNote
          );
        }
      }
    });

    EventBus.$on("homeBtnClicked", () => {
      this.uncheckedCustomAmount = "";
      this.selectedNote = 0;
      CheckModalStore.setIsError(false);
      CheckModalStore.setErrorMessage("");
      CheckModalStore.setIsSuccess(false);
    });
  }

  setSelectedMenu(menu: Menu): void {
    AtmStore.setSelectedMenu(menu);
  }

  customAmountChanged(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    const customValidation = value.match("^[0-9]*$");
    const isValid = customValidation !== null && customValidation.length > 0;
    if (!isValid) {
      (event.target as HTMLInputElement).value = value.substring(0, value.length - 1);
      return;
    }
    this.uncheckedCustomAmount = value;
  }

  noteClicked(note: number): void {
    this.uncheckedCustomAmount = "";
    this.selectedNote = note;
    CheckModalStore.setIsError(false);
    CheckModalStore.setErrorMessage("");
    CheckModalStore.setIsSuccess(false);
  }

  clearSelectedNote(): void {
    this.selectedNote = 0;
  }
}

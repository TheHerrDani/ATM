import { Component, Vue } from "vue-property-decorator";

import atmStore from "@/store/atmStore";

@Component({})
export default class InfoUI extends Vue {
  get accountNumber() {
    return atmStore.getAccountNumber;
  }
  get balance() {
    return atmStore.getAccountInformation.actualBalance;
  }

  get overdraft() {
    return  atmStore.getAccountInformation.overdraft;
  }

  get dispensedMoney() {
    return  atmStore.getLastDispenseInformation.dispensedAmount;
  }
  get remainingDispensableAmount() {
    return (
       atmStore.getLastDispenseInformation.remainingDispensableAmount
    );
  }
  get euroFiftyCount() {
    return atmStore.getLastDispenseInformation.euroFiftyCount;
  }
  get euroTwentyCount() {
    return atmStore.getLastDispenseInformation.euroTwentyCount;
  }
  get euroTenCount() {
    return atmStore.getLastDispenseInformation.euroTenCount;
  }
  get euroFiveCount() {
    return atmStore.getLastDispenseInformation.euroFiveCount;
  }
}

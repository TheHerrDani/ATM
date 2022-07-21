import AccountDataModel from "@/types/AccountDataModel";
import { Vue } from "vue-property-decorator";
import CallHandler from "./CallHandler";
import AtmStore from "../store/atmStore";
import DispenseMoneyModel from "@/types/DispenseMoneyModel";
import DispenseDataModel from "@/types/DispenseDataModel";
import CheckModalStore from "../store/checkModalStore";
import callHandlerStore from "@/store/callHandlerStore";
import Axios from "axios-observable";
import atmStore from "../store/atmStore";
import AtmApi from "./AtmApi";

export default class AccountApi extends Vue {
  private static accountApiInstance: AccountApi;

  get databaseHandler(): Axios | null {
    return callHandlerStore.getCallHandler;
  }

  public async information(accountNumber: string) {
    try {
      if (this.databaseHandler) {
        const params = { params: { accountNumber: accountNumber } };
        this.databaseHandler
          .get<AccountDataModel>("/api/account/information", params)
          .subscribe({
            next: (response) => AtmStore.setAccountInformation(response.data),
            error: (error) => console.error(error.response.data),
          });
      }
    } catch (exp) {
      console.error(exp);
    }
  }

  public async dispenseMoney(accountNumber: string, requestedMoney: number) {
    let dispenseMoneyModel: DispenseMoneyModel = new DispenseMoneyModel();
    dispenseMoneyModel.accountNumber = accountNumber;
    dispenseMoneyModel.atmId = atmStore.getATM_ID;
    dispenseMoneyModel.requestedMoney = requestedMoney;
    try {
      console.log("Databasehandler", this.databaseHandler);
      if (this.databaseHandler) {
        this.databaseHandler
          .post<DispenseDataModel>(
            "/api/account/dispense-money",
            dispenseMoneyModel
          )
          .subscribe({
            next: async (response) => {
              await AtmStore.setLastDispenseInformation(response.data);
              AccountApi.getAccountApiInstance().information(
                atmStore.getAccountNumber
              );
              AtmApi.getAtmApiInstance().setAtmStateInformation(
                atmStore.getATM_ID
              );
              CheckModalStore.setIsSuccess(true);
            },
            error: (error) => {
              console.log(error);
              CheckModalStore.setIsError(true);
              CheckModalStore.setErrorMessage(error.response.data);
              CheckModalStore.setIsSuccess(false);
            },
          });
      }
    } catch (exp) {
      console.error(exp);
    }
  }

  static getAccountApiInstance(): AccountApi {
    if (AccountApi.accountApiInstance) {
      return AccountApi.accountApiInstance;
    } else {
      return (AccountApi.accountApiInstance = new AccountApi());
    }
  }
}

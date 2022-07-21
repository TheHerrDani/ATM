import {
  Action,
  getModule,
  Module,
  Mutation,
  VuexModule
} from "vuex-module-decorators";

import { Menu } from "@/types";
import AccountDataModel from "@/types/AccountDataModel";
import AtmDataModel from "@/types/AtmDataModel";
import DispenseDataModel from "@/types/DispenseDataModel";
import store from "./store";

@Module({ dynamic: true, namespaced: true, name: "AtmStore", store })
class AtmStore extends VuexModule {
  private ATM_ID: number = 1;
  private accountNumber = "0";
  private accountInformation: AccountDataModel | null = null;
  private atmStateInformation: AtmDataModel | null = null;
  private lastDispenseInformation: DispenseDataModel | null = null;
  private selectedMenu = Menu.HOME;
  private withdrawalAmount = "";

  public get getATM_ID(): number {
    return this.ATM_ID;
  }

  public get getAccountNumber(): string {
    return this.accountNumber;
  }

  public get getSelectedMenu(): Menu {
    return this.selectedMenu;
  }

  public get getWithdrawalAmount(): string {
    return this.withdrawalAmount;
  }

  public get getAccountInformation(): AccountDataModel {
    if (this.accountInformation) {
      return this.accountInformation;
    } else {
      return new AccountDataModel();
    }
  }

  public get getAtmStateInformation(): AtmDataModel {
    if (this.atmStateInformation) {
      return this.atmStateInformation;
    } else {
      return new AtmDataModel();
    }
  }

  public get getLastDispenseInformation(): DispenseDataModel {
    if (this.lastDispenseInformation) {
      return this.lastDispenseInformation;
    } else {
      return new DispenseDataModel();
    }
  }

  @Mutation
  private setAccountInformationMutation(accountDataModel: AccountDataModel) {
    this.accountInformation = accountDataModel;
  }

  @Mutation
  private setAtmStateInformationtMutation(atmDataModel: AtmDataModel) {
    this.atmStateInformation = atmDataModel;
  }

  @Mutation
  private setLastDispenseInformationMutation(
    dispenseDataModel: DispenseDataModel
  ) {
    this.lastDispenseInformation = dispenseDataModel;
  }

  @Mutation
  private setSelectedMenuMutation(menu: Menu) {
    this.selectedMenu = menu;
  }

  @Mutation
  private setWithdrawalAmountMutation(withdrawalAmount: string) {
    this.withdrawalAmount = withdrawalAmount;
  }

  @Mutation
  private setAccountNumberMutation(accountNumber: string) {
    this.accountNumber = accountNumber;
  }

  @Action({ commit: "setAccountInformationMutation", rawError: true })
  public setAccountInformation(accountDataModel: AccountDataModel) {
    return accountDataModel;
  }

  @Action({ commit: "setAtmStateInformationtMutation", rawError: true })
  public setAtmStateInformation(atmDataModel: AtmDataModel) {
    return atmDataModel;
  }

  @Action({ commit: "setLastDispenseInformationMutation", rawError: true })
  public async setLastDispenseInformation(
    dispenseDataModel: DispenseDataModel
  ) {
    return dispenseDataModel;
  }

  @Action({ commit: "setSelectedMenuMutation", rawError: true })
  public setSelectedMenu(menu: Menu) {
    return menu;
  }

  @Action({ commit: "setWithdrawalAmountMutation", rawError: true })
  public setWithdrawalAmount(withdrawalAmount: string) {
    return withdrawalAmount;
  }

  @Action({ commit: "setAccountNumberMutation", rawError: true })
  public async setAccountNumber(accountNumber: string) {
    return accountNumber;
  }
}
export default getModule(AtmStore);

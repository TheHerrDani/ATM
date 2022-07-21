import {
  Action,
  getModule,
  Module,
  Mutation,
  VuexModule
} from "vuex-module-decorators";

import store from "./store";

@Module({ dynamic: true, namespaced: true, name: "CheckModalStore", store })
class CheckModalStore extends VuexModule {
  private isError: boolean = false;
  private errorMsg: string = "";
  private isSuccess: boolean = false;

  public get isErrorTrue(): boolean {
    return this.isError;
  }

  public get getErrorMessage(): string {
    return this.errorMsg;
  }

  public get isSuccessTrue(): boolean {
    return this.isSuccess;
  }

  @Mutation
  private setIsErrorMutation(isError: boolean) {
    this.isError = isError;
  }

  @Mutation
  private setErrorMessageMutation(errorMsg: string) {
    this.errorMsg = errorMsg;
  }

  @Mutation
  private setIsSuccessMutation(isSuccess: boolean) {
    this.isSuccess = isSuccess;
  }

  @Action({ commit: "setIsErrorMutation", rawError: true })
  public setIsError(isError: boolean) {
    return isError;
  }

  @Action({ commit: "setErrorMessageMutation", rawError: true })
  public setErrorMessage(errorMsg: string) {
    return errorMsg;
  }

  @Action({ commit: "setIsSuccessMutation", rawError: true })
  public setIsSuccess(isSuccess: boolean) {
    return isSuccess;
  }
}
export default getModule(CheckModalStore);

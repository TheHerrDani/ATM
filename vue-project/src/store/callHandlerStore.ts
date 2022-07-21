import Axios from "axios-observable";
import {
  Action,
  getModule,
  Module,
  Mutation,
  VuexModule
} from "vuex-module-decorators";

import store from "./store";

@Module({ dynamic: true, namespaced: true, name: "callHandlerStore", store })
class callHandlerStore extends VuexModule {
  private callHandler: Axios | null = null;

  public get getCallHandler(): Axios | null {
    return this.callHandler;
  }

  @Mutation
  private setCallHandlerMutation(callHandler: Axios) {
    this.callHandler = callHandler;
  }

  @Action({ commit: "setCallHandlerMutation", rawError: true })
  public async setCallHandler(callHandler: Axios) {
    return callHandler;
  }
}
export default getModule(callHandlerStore);

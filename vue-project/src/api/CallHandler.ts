import Axios from "axios-observable";
import { Vue } from "vue-property-decorator";
export default class CallHandler extends Vue {

  static async createCallHandler(config: {
    headers: { Authorization: string };
  }): Promise<Axios> {
    return Axios.create({
      baseURL: "http://localhost:8080/",
      timeout: 1000,
      headers: config.headers,
    });
  }
}

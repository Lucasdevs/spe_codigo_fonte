import { SelectItem } from "primeng";

export class MethodUtil {

  static preencherDropdown(value: string, label: string) {
    const selectItem: SelectItem = {
      value, label
    };
    return selectItem;
  }

}
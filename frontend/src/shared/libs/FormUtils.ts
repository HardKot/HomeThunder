export class FormUtils {
  static fromObject<T extends object>(data: T) {
    return Object
          .entries(data)
          .reduce((form, [key, value]) => {
            form.set(key, value);
            return form
          }, new FormData())
  }

}

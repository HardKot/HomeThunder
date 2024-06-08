export const RuleRouting = (path: string) => {
  switch (true) {
    case /\/role(\/[\d\w-]+|)/g.test(path):
      return ["roleCreated", "roleChange", "roleDeleted"];

    default:
      return [];
  }
};

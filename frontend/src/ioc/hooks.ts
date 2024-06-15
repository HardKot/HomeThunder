import { useInjection } from "inversify-react";
import { TYPES } from "./types";

export const useCsrfToken = () => useInjection<string>(TYPES.CSRFToken)

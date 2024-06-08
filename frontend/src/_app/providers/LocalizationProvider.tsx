"use client";

import { PropsWithChildren } from "react";
import { LocalizationProvider as MUILocalizationProvider } from "@mui/x-date-pickers";
import { AdapterMoment } from "@mui/x-date-pickers/AdapterMoment";

export const LocalizationProvider = ({ children }: PropsWithChildren) => (
  <MUILocalizationProvider dateAdapter={AdapterMoment} adapterLocale={"ru"}>
    {children}
  </MUILocalizationProvider>
);

import { PropsWithChildren } from "react";
import { ThemeProvider } from "@/app/providers/ThemeProvider";
import { LocalizationProvider } from "@/app/providers/LocalizationProvider";

export const CombineProvider = ({ children }: PropsWithChildren) => (
  <ThemeProvider>
    <LocalizationProvider>{children}</LocalizationProvider>
  </ThemeProvider>
);

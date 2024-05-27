import { PropsWithChildren } from "react";
import { ThemeProvider } from "@/app/providers/ThemeProvider";
import { LocalizationProvider } from "@/app/providers/LocalizationProvider";
import { StateStoreProvider } from "@/app/providers/StateStoreProvider";

export const CombineProvider = ({
  children,
}: PropsWithChildren) => (
  <StateStoreProvider>
    <ThemeProvider>
      <LocalizationProvider>{children}</LocalizationProvider>
    </ThemeProvider>
  </StateStoreProvider>
);

import { PropsWithChildren } from "react";
import { ThemeProvider } from "@/app/providers/ThemeProvider";
import { LocalizationProvider } from "@/app/providers/LocalizationProvider";
import { IocProvider } from "./IocProvider";
import { headers } from "next/headers";

export const CombineProvider = ({ children }: PropsWithChildren) => (
  <ThemeProvider>
    <IocProvider csrfToken={headers().get('X-CSRF-Token') || 'missing'}>
      <LocalizationProvider>{children}</LocalizationProvider>
      </IocProvider>
  </ThemeProvider>
);

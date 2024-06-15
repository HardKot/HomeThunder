"use client"
import { PropsWithChildren } from "react";
import { Provider } from "inversify-react"
import { appContainer } from "@/ioc/inversify.appContainer";
import { TYPES } from "@/ioc";
import { Container } from "inversify";

interface IocProviderProps extends PropsWithChildren {
  csrfToken: string
}

export const IocProvider = ({ children, csrfToken }: IocProviderProps) => (
  <Provider container={() => {
    const container = new Container();
    container.bind(TYPES.CSRFToken).toConstantValue(csrfToken);
    return container;
  }}
  >
    {children}
  </Provider>
)

import { useCallback, useRef } from "react"
import { EventBus, Event } from "./EventBus"

export const useEventBus = <T>(eventName: string) => {
	const eventBusRef = useRef(EventBus);
	
	const on = useCallback((callback: (event: Event<T>) => void) => {
		return eventBusRef.current.on(eventName, callback);
	}, [eventName, eventBusRef]);
	
	const send = useCallback((payload: T) => {
		eventBusRef.current.send(eventName, payload);
	}, [eventName, eventBusRef]);


	return { on, send }
}

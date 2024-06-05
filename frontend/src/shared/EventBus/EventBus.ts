export type Event<T> = { payload?: T, timeSend: Date }

export class EventBus {

	private static listListner: {[eventName: string]: Map<Symbol, { callback: (event: Event<any>) => void }>} = {};

	static on = <T>(name: string, callback: (event: Event<T>) => void) => {
		if (!this.listListner[name]) this.listListner[name] = new Map();
		const subscriptionKey = Symbol();
		this.listListner[name].set(subscriptionKey, { callback });
			
		return () => {
			this.listListner[name].delete(subscriptionKey);		
		}
	}

	static send = (name: string, payload?: any) => {
		if (!this.listListner[name]) this.listListner[name] = new Map();
		for (const { callback } of this.listListner[name].values()) {
			try {
				callback({
					payload,
					timeSend: new Date(),
				})
			} catch (error) {
				return false;
			}
		}
		return true;
	}
}

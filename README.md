# SwiftNG

A refined Android proxy client, customized from [v2rayNG](https://github.com/2dust/v2rayNG) with a focus on speed, clarity, and one-tap control.

## Highlights

- **Widget-style home** — Dark, rounded-card interface inspired by iOS widgets. Status, speed, and node info at a glance.
- **Status card as switch** — Tap the bottom status card to connect or disconnect. No floating buttons.
- **Auto-select fastest node** — One tap tests all nodes and switches to the lowest-latency one, reconnecting automatically.
- **Live latency** — Current node's latency is tested automatically on connect and refreshed every 60 seconds.
- **Real-time speed** — Live up/down throughput, refreshed every 2 seconds.
- **IPv4 / IPv6 badge** — Current node card shows which stack the traffic is actually using.
- **Clean navigation** — No drawer clutter. Four large shortcuts at the bottom: Per-app Proxy, Routing, Assets, Settings.

## Build

GitHub Actions builds a signed `arm64-v8a` APK on every push to `master`. Grab the latest from [Releases](https://github.com/cien121/SwiftNG/releases).

## Credits

- Upstream: [2dust/v2rayNG](https://github.com/2dust/v2rayNG)
- Core: [XTLS/Xray-core](https://github.com/XTLS/Xray-core)

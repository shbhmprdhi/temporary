# Chillio APK reference analysis

Input file: `chillio-tv.apk` supplied in this conversation. Analysis was static; the APK was not executed.

- Package: `app.lumoslabs.chillio`
- Version name: `1.4.0`
- Main activity: `app.lumoslabs.chillio.MainActivity`
- UI framework clues: Jetpack Compose/navigation strings appear in DEX files.
- Home/discovery clues: `HomeScreen`, `Featured`, `DiscoveryGrid`, `v1/media/discovery`, `featured_movie`, and `continue watching items` appear in strings.
- Preferences/clues: `hiddenDiscoverySections`, `home_last_selected_tab`, `poster-layout-show-titles`, `poster-layout-show-year`, `poster-layout-ratings`, and `posterFocusLabels`.
- Account/paywall clues: account portal strings, RevenueCat-related identifiers, `premium`, `premium_lifetime`.

Interpretation: this supports a feature inventory and some architectural inferences, but not exact screenshots or full runtime behavior. The resources contain obfuscated filenames, and this environment did not have JADX/APKTool available for complete source/resource decompilation.

Recommended additional input for a higher-fidelity UI reference: screenshots from a running Chillio installation (Home screen, left/right/up/down focus states, details view, Movies/Shows tabs, settings). Do not share credentials, device-linking codes, tokens, or private manifest URLs.

The new implementation should use the feature inventory as guidance, with independently written code and original/licensed assets.
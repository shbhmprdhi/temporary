# Implementation plan: Chillio-inspired Nuvio home

## Status
- [x] Inspect the uploaded Chillio APK archive, DEX printable strings, and manifest metadata.
- [x] Identify Nuvio home screen source files and public upstream branch.
- [x] Write the clean-room design brief and home screen behavior specification.
- [x] Add a standalone Jetpack Compose prototype matching the screenshot's top navigation, category tiles, Continue Watching, and Favorites layout.
- [ ] Create an actual fork or working checkout of Nuvio source with write access.
- [ ] Adapt the prototype to Nuvio's real `HomeUiState` and existing navigation/playback callbacks.
- [ ] Build and run the modified APK on Android TV.

## Confirmed source files
- Upstream repository: https://github.com/NuvioMedia/NuvioTV (branch `dev`)
- Home screen: `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeScreen.kt`
- Home UI state: `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeUiState.kt`
- Modern presentation adapter: `app/src/main/java/com/nuvio/tv/ui/screens/home/ModernHomePresentation.kt`
- README build command: `./gradlew :app:assembleFullDebug`

## Prototype added
- `ChillioInspiredHome.kt` — a clean-room, data-driven Compose UI prototype. It defines lightweight media/category models and a callback-driven home layout. It is a design/implementation starting point, not yet wired into Nuvio's production state or routes, and has not been compiled in the upstream project.
- The current screenshot is the source of truth for layout: centered top segmented navigation, greeting/profile on the left, date/account on the right, image-backed category tiles, compact landscape Continue Watching cards with progress, and portrait Favorites posters.

## What static inspection supports
The Chillio APK's manifest identifies package `app.lumoslabs.chillio`, version `1.4.0`, and main activity `app.lumoslabs.chillio.MainActivity`. DEX strings contain `HomeScreen`, `Featured`, `DiscoveryGrid`, `v1/media/discovery`, `featured_movie`, `continue watching items`, and settings identifiers for hidden discovery sections and poster title/year/rating display. This is a feature map, not a claim of exact pixel-level fidelity.

## Integration approach
Adapt the existing `HomeUiState` and `ModernHomePresentationState` into the prototype models while preserving Nuvio's existing view-model contracts, catalog item types, detail navigation, and playback callback paths. Replace mock data with actual catalog, continue-watching, and library/favorites content only after confirming the correct existing data source. Do not use copied Chillio compiled code or bundled assets.

## Build and verification
1. Start from a clean checkout of upstream `dev`.
2. Integrate the prototype in small, reviewable changes.
3. Build using `./gradlew :app:assembleFullDebug`.
4. Test on emulator/device at 1080p and 720p using D-pad focus, Enter, Back, long press, playback, and empty-catalog cases.
5. Preserve upstream GPL-3.0 notices and comply with license requirements if distributing a derivative.

## Access blocker
The connected GitHub context can read `NuvioMedia/NuvioTV` but does not have push permission there. The current feature branch is in `shbhmprdhi/temporary`, which is a scratch repo rather than a Nuvio fork. The prototype has been committed there so it can be reviewed, but it is not yet a Nuvio source modification or an APK.

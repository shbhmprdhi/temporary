# Implementation plan: Chillio-inspired Nuvio home

## Status
- [x] Inspect the uploaded Chillio APK archive, DEX printable strings, and manifest metadata.
- [x] Identify Nuvio home screen source files and public upstream branch.
- [x] Write the clean-room design brief and home screen behavior specification.
- [ ] Create an actual fork or working checkout of Nuvio source with write access.
- [ ] Make the first Compose UI changes in a feature branch.
- [ ] Build and run the modified APK on Android TV.

## Confirmed source files
- Upstream repository: https://github.com/NuvioMedia/NuvioTV (default branch `dev`)
- Home screen: `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeScreen.kt`
- Home UI state: `app/src/main/java/com/nuvio/tv/ui/screens/home/HomeUiState.kt`
- Modern presentation adapter: `app/src/main/java/com/nuvio/tv/ui/screens/home/ModernHomePresentation.kt`
- README build command: `./gradlew :app:assembleFullDebug`

## What static inspection supports
The Chillio APK's manifest identifies package `app.lumoslabs.chillio`, version `1.4.0`, and main activity `app.lumoslabs.chillio.MainActivity`. DEX strings contain `HomeScreen`, `Featured`, `DiscoveryGrid`, `v1/media/discovery`, `featured_movie`, `continue watching items`, and settings identifiers for hidden discovery sections and poster title/year/rating display. This is a feature map, not a claim of exact pixel-level fidelity.

## Code approach
The next implementation should add/rework presentation components within Nuvio's home package while keeping existing view-model contracts, catalog item types, detail navigation, and playback callback paths. It should start as a UI-only change using Nuvio data that is already present. Do not use copied Chillio compiled code or bundled assets.

## Build and verification
1. Confirm a clean full-debug build on the unmodified checkout.
2. Implement one home-screen layer at a time: background/hero, catalog rows, then focus behavior.
3. Build using `./gradlew :app:assembleFullDebug`.
4. Test on emulator/device at 1080p and 720p using D-pad, Enter, Back, long press, playback, and empty-catalog cases.
5. Preserve upstream GPL-3.0 notices and comply with license requirements if distributing a derivative.

## Access blocker
The connected GitHub context can read `NuvioMedia/NuvioTV` but reports no push permission on that upstream repo. The current feature branch is in `shbhmprdhi/temporary` and contains planning documents only. It is not yet a Nuvio fork, a code modification, or an APK.
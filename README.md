# exoplayer-sample
Sample app like Youtube.

| full | short |
| ---- | ----- |
| <image src="https://user-images.githubusercontent.com/69252773/138678463-0e7bc9e6-5bae-4e9e-9113-a3ed9a3f6e1b.jpg" width="240x" /> | <image src="https://user-images.githubusercontent.com/69252773/138679348-48360c0d-31ff-4445-b5f0-f17233d596ce.jpg" width="240x" /> |


# Features
- PlayerView is fixedly top of layout (not scroll)
- ListView or RecycerView (something scroll items) is under the player
- Full screen mode, when button in the controller view is tapped or screen orientation changes
- Choose video type
  - Short (this supposes showing trial or billing user flow at the end of video) 
  - Full (Prevalent video features that have playlists like YouTube)

# References
### ExoPlayer
- https://exoplayer.dev/doc/reference/com/google/android/exoplayer2/ui/PlayerView.html

### Configration Changing, full screen
- https://developer.android.com/guide/topics/resources/runtime-changes?hl=ja
- https://stackoverflow.com/questions/22848222/toggling-fullscreen-orientation-like-youtube
- https://j3iiifn.hatenablog.com/entry/2020/08/17/000000
- https://qiita.com/kiito1000/items/46bd861c3c3dd3220366#%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E8%A8%AD%E5%AE%9A%E3%82%92%E5%8F%96%E5%BE%97%E3%81%99%E3%82%8B

### Playlist, auto transition, window index
- https://stackoverflow.com/questions/40284772/exoplayer-2-playlist-listener
- https://qiita.com/niusounds/items/5252df4af9754388aa35

### View, ...etc
- https://stackoverflow.com/questions/40494623/android-imageview-169

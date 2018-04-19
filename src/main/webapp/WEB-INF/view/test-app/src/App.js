import React, { Component } from 'react';
import './App.css';
import Movie from './Movie'

class App extends Component {
	/**
	 * [Render]
	 * description: 컴포넌트가 존재하기 시작할때 작동
	 * step.1 componentWillMount(): render 되기전 호출 (ex. api, ajax등 호출)
	 * step.2. render(): 화면에 그리기
	 * step.3. componentDidMount(): render 후 호출 (ex. databind?)

	 * [Update]
	 * description:
	 * step.1. componentWillReceiveProps(): 컴포넌트가 새로운 props를 받음
	 * step.2. shouldComponentUpdate(): old props, news props 비교 후 다르면 true라고 판단
	 * step.3. componentWillUpdate(): 2단계에서 true라고 판단되면 props update (ex. spinner 호출)
	 * step.4. render(): 화면에 그리기
	 * step.5. componentDidUpdate(): render 후 호출 (ex. spinner 제거)
	 */

	state = {};

	componentDidMount() {
		this._getMovies();
	}

	_renderMovies = () => {
		const movies = this.state.movies.map((movie, index) => {
			return (
				<Movie
					title={movie.title_english}
					poster={movie.medium_cover_image}
					key={movie.id}
					genres={movie.genres}
					synopsis={movie.synopsis}
				/>
			)
		})
		return movies
	}

	_getMovies = async () => {
		const movies = await this._callApi();
		this.setState({
			movies
		});
	}

	_callApi = () => {
		return fetch('https://yts.am/api/v2/list_movies.json?sort_by=download_count')
			.then(response => response.json())
			.then(json => json.data.movies)
			.catch(err => console.log('error ::', err))
	}



	render() {
		const { movies } = this.state;
		return (
			<div className={movies ? "App" : "App--loading"}>
				{movies ? this._renderMovies() : 'Loading..'}
			</div>
		);
	}
}

export default App;
